package com.example.messagingrabbitmq;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;
    private final Interesting interesting;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate, Interesting interesting) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
        this.interesting = interesting;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println();
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(MessagingRabbitmqApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
        receiver.getLatch().await(100000, TimeUnit.MILLISECONDS);
        interesting.getLatch().await(100000, TimeUnit.MILLISECONDS);

    }

}