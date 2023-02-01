package myticket.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import myticket.response.UserResponse;
import myticket.request.LoginRequest;
import myticket.request.UserRequest;

import myticket.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserResponse>> getAll() {
		return ResponseEntity.ok(userService.getAll());
	}

	@PostMapping

	public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {

		Logger logger = Logger.getLogger(UserController.class.getName());
		UserResponse userResponse = userService.createUser(userRequest);

		logger.log(Level.INFO, "user created: {0}", userResponse);
		return ResponseEntity.ok(userResponse);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(userService.login(loginRequest));
	}
}
