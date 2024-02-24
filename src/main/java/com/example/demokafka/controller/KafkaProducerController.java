package com.example.demokafka.controller;

import com.example.demokafka.dto.UserDto;
import com.example.demokafka.service.KafkaProducer;
import com.example.demokafka.values.AppValues;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
@RequiredArgsConstructor
public class KafkaProducerController {

    private final KafkaProducer kafkaProducer;

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody UserDto message){
        kafkaProducer.sendMessage(AppValues.TOPIC, message);
        return ResponseEntity.ok("Message sent to kafka topic");
    }
}
