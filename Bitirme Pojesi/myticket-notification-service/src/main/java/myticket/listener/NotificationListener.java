package myticket.listener;

import org.springframework.stereotype.Component;
import myticket.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


@Component
public class NotificationListener {
	
	@RabbitListener(queues= "myticket.notification")
public void notificationListener(User user) {
		System.out.printf("%s ,%s , %s", user.getName(), user.getEmail(), user.getPassword() );
}
	

}
