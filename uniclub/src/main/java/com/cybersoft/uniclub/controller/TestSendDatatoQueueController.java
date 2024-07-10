package com.cybersoft.uniclub.controller;

import com.cybersoft.uniclub.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestSendDatatoQueueController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @GetMapping("")
    public ResponseEntity<?> testQueue(){
        rabbitTemplate.convertAndSend("excode04" ,"/queuecode04","Hello my friend");
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
