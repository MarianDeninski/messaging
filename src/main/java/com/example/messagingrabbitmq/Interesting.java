package com.example.messagingrabbitmq;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;




@Component
public class Interesting {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Interesting <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
