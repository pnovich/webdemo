package com.example.webdemo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

//@Component
public class Consumer {

    @JmsListener(destination = "standalone.queue")
    public void listenerDefault(String message){
        System.out.println("message from default que: " + message);
    }

//    @JmsListener(destination = "queue1.queue")
//    public void listenerQueue1(String message){
//        System.out.println("message from queue1: " + message);
//    }

    @JmsListener(destination = "queue2.queue")
    public void listenerQueue2(String message){
        System.out.println("message from queue2: " + message);
    }

}

