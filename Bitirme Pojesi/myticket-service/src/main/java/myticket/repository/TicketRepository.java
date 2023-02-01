package myticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import myticket.model.Ticket;

import java.util.List;
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	List<Ticket> findAllByUserId(int id);

}
