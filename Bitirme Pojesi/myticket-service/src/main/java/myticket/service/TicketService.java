package myticket.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import myticket.controller.UserController;
import myticket.model.Ticket;
import myticket.model.enums.UserType;
import myticket.model.User;
import myticket.repository.TicketRepository;
import myticket.exception.UserNotFoundException;
import myticket.exception.MyTicketException;
import myticket.request.TicketRequest;

public class TicketService {
	

	private static final int MAX_INDIVICUAL_TICKET_SIZE= 5;
	
	@Autowired
	private UserService userService;

	@Autowired
	private TicketRepository ticketRepository;
	
	Logger logger = Logger.getLogger(UserController.class.getName());

	public Ticket create(TicketRequest ticketRequest) {

		User foundUser = userService.getById(ticketRequest.getUserId())
				.orElseThrow(() -> new UserNotFoundException("user not found"));

		validateIndividualRealtySize(foundUser);
		
		
		Ticket ticket = convert(ticketRequest);
		ticket.setUser(foundUser);
		Ticket savedTicket = ticketRepository.save(ticket);

		System.out.println("createTicket :: " + ticket);
		
		return savedTicket;
		
	}

	private void validateIndividualRealtySize(User foundUser) {
		if (UserType.INDIVIDUAL.equals(foundUser.getType())) { 

			List<Ticket> ticketList = ticketRepository.findAllByUserId(foundUser.getId());

			if (MAX_INDIVICUAL_TICKET_SIZE == ticketList.size()) {

				logger.log(Level.WARNING, " userID : {0}",
						foundUser.getId());

				throw new MyTicketException("indivual.user.ticket.max.size");
			}
		}
	}
	public List<Ticket> getAllById(int id) {
		return ticketRepository.findAllByUserId(id);
	}
	
	private Ticket convert(TicketRequest ticketRequest) {
		Ticket ticket = new Ticket();
		ticket.setId(ticketRequest.getId());
		ticket.setName(ticketRequest.getNo());
		ticket.setExpeditionInformation(ticketRequest.getExpeditionInformation());
		return ticket;
	}
	
	public List<Ticket> getAll() {
		return ticketRepository.findAll();
	}
	
	
}
