package pl.lcappsdev.rabbitmqlistener;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMqConfig {

	private static final String MY_QUEUE = "MyQueue";
	
	@Bean
	Queue myQueue() {
		return new Queue(MY_QUEUE, true);
	}
	
	
	@Bean
	ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		return connectionFactory;
	}
	
	@Bean
	MessageListenerContainer messageListenerContainer() {
		
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
		simpleMessageListenerContainer.setQueues(myQueue());
		simpleMessageListenerContainer.setMessageListener(new RabbitMqMessageListener());
		
		return simpleMessageListenerContainer;
	}
}
