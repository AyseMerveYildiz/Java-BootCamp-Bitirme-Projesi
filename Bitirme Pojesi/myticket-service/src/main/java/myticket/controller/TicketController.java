package myticket.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myticket.model.Ticket;
import myticket.request.TicketRequest;
import myticket.service.TicketService;

@RestController
@RequestMapping(value = "/tickets")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@GetMapping
	public ResponseEntity<List<Ticket>> getAll() {
		return ResponseEntity.ok(ticketService.getAll());
	}

	@PostMapping
	public ResponseEntity<Ticket> create(@RequestBody TicketRequest ticketRequest) {
		Ticket realty = ticketService.create(ticketRequest);
		return new ResponseEntity<>(realty, HttpStatus.CREATED);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<List<Ticket>> getAllByUserId(@PathVariable int id) {
		List<Ticket> tickets = ticketService.getAllById(id);
		return ResponseEntity.ok(tickets);
	}
	
}
