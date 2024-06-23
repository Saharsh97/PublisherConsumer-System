package com.scaler.publisherconsumer;

import com.scaler.publisherconsumer.models.Consumer;
import com.scaler.publisherconsumer.models.Publisher;
import com.scaler.publisherconsumer.models.Queue;
import com.scaler.publisherconsumer.models.Topic;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PublisherConsumerApplication implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		Queue queue = Queue.getInstance();
		Topic topic1 = new Topic(1L);
		Topic topic2 = new Topic(2L);
		queue.addTopic(topic1);
		queue.addTopic(topic2);

		Consumer consumer1 = new Consumer(100L, "consumer1", topic1);
		Consumer consumer2 = new Consumer(200L, "consumer2", topic2);
		new Thread(consumer1).start();
		new Thread(consumer2).start();

		Publisher publisher0 = new Publisher(10L, "publisher0");
		Publisher publisher1 = new Publisher(11L, "publisher1");
		Publisher publisher2 = new Publisher(12L, "publisher2");

		publisher0.publish("p0 hello1", topic1.getId());
		Thread.currentThread().sleep(3 * 1000);
		publisher0.publish("p0 hello2", topic2.getId());
		Thread.currentThread().sleep(3 * 1000);

		publisher1.publish("p1 foobar1", topic1.getId());
		publisher1.publish("p1 foobar2", topic1.getId());
		publisher2.publish("p2 query1", topic1.getId());
		publisher2.publish("p2 query2", topic1.getId());

		Thread.sleep(10 * 1000);
		consumer1.setOffset(0);
		System.out.println("consumer 1 reset");
	}

	public static void main(String[] args) {
		SpringApplication.run(PublisherConsumerApplication.class, args);
	}
}
