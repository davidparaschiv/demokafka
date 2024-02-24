package com.example.demokafka.service;

import com.example.demokafka.dto.UserDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@Getter
public class UserUpdateHandlingService {

    private final List<UserDto> mockDb = new ArrayList<>();

    public void handle(UserDto userDto) {
        mockDb.add(userDto);
        log.info("UserUpdateHandlingService->handle");
    }
}
