package myticket.service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import myticket.repository.UserRepository;

import myticket.configuration.RabbitMQConfiguration;
import myticket.converter.UserConverter;
import myticket.response.UserResponse;
import myticket.controller.UserController;
import myticket.exception.UserNotFoundException;
import myticket.model.User;
import myticket.repository.UserRepository;
import myticket.request.LoginRequest;
import myticket.request.UserRequest;
import myticket.response.UserResponse;
import myticket.util.PasswordUtil;
import java.util.List;

public class UserService {
	private static final String EMAIL_OR_PASSWORD_ARE_WRONG= "Email veya şifre yanlış";

	private static final String LOGIN_IS_SUCCESSFULL = "Login Başarılı";

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private RabbitMQConfiguration rabbitMQConfiguration;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter converter;
	
	public UserResponse createUser(UserRequest userRequest) {
		Logger logger = Logger.getLogger(UserController.class.getName());

		String hash = PasswordUtil.preparePasswordHash(userRequest.getPassword(), userRequest.getEmail());

		logger.log(Level.INFO, "[createUser] - password hash created: {0}", hash);

		User savedUser = userRepository.save(converter.convert(userRequest, hash));

		logger.log(Level.INFO, "[createUser] - user created: {0}", savedUser.getId());
		
		rabbitTemplate.convertAndSend(rabbitMQConfiguration.getQueueName(), userRequest);

		logger.log(Level.WARNING, "[createUser] - userRequest: {0}, sent to : {1}",
				new Object[] { userRequest.getEmail(), rabbitMQConfiguration.getQueueName() });
		return converter.convert(savedUser);
	}

	public List<UserResponse> getAll() {
		return converter.convert(userRepository.findAll());
	}
	
	public Optional<User> getById(Integer userId) {
		return userRepository.findById(userId);
	}

	public String login(LoginRequest loginRequest) {

		User foundUser = userRepository.findByEmail(loginRequest.getEmail())
				.orElseThrow(() -> new UserNotFoundException("user not found"));

		

		String passwordHash = PasswordUtil.preparePasswordHash(loginRequest.getPassword(), loginRequest.getEmail());

		boolean isValid = PasswordUtil.validatePassword(passwordHash, foundUser.getPassword());

		return isValid ? LOGIN_IS_SUCCESSFULL : EMAIL_OR_PASSWORD_ARE_WRONG;

	}
}
