package com.example.webdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController {
    @Autowired
    PersonRepository personRepository;
    @GetMapping("/")
    public String getDefaultResult(){
        return "default result";
    }

    @GetMapping("/special")
    public String getSpecialResult(){
        return "special result";
    }

    @GetMapping("/list")
    public List<Person> getPersonsList(){
//        List<Person> personList = new ArrayList<Person>();
//        Person person1 = new Person(1L,"Vasia");
//        Person person2 = new Person(2L,"Dasha");
//        personList.add(person1);
//        personList.add(person2);
        return personRepository.findAll();
    }
    @GetMapping("/create")
    String getNewPerson(){
        Person person = new Person();
        person.setName("nnnn");
        personRepository.save(person);
        return "created " + person;
    }

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
            @Qualifier("default")
    Queue queue;

    @Autowired
            @Qualifier("queue1")
    Queue queue1;

    @Autowired
            @Qualifier("queue2")
    Queue queue2;


    @GetMapping("/publish/{message}")
    public String publishMessageToJms(@PathVariable("message")
                                          final String message){
        jmsTemplate.convertAndSend(queue, message);
        return "published";
    }

    @GetMapping("/push1/{message}")
    public String publishMessageToQueue1(@PathVariable("message")
                                      final String message){
        jmsTemplate.convertAndSend(queue1, message);
        return "published";
    }

    @GetMapping("/push2/{message}")
    public String publishMessageToQueue2(@PathVariable("message")
                                      final String message){
        jmsTemplate.convertAndSend(queue2, message);
        return "published";
    }

    @GetMapping("/push/{message}")
    public String publishMessageToQueue(@PathVariable("message")
                                      final String message){
        jmsTemplate.convertAndSend(queue1, message);
        jmsTemplate.convertAndSend(queue2, message);
        return "published";
    }

}
