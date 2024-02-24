package com.example.demokafka.service;

import com.example.demokafka.dto.UserDto;
import com.example.demokafka.values.AppValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final UserUpdateHandlingService handlingService;

    @KafkaListener(topics = AppValues.TOPIC, groupId = AppValues.GROUP_ID)
    public void consume(UserDto data){
        log.info(String.format("Message received -> %s", data.toString()));
        handlingService.handle(data);
    }
}
