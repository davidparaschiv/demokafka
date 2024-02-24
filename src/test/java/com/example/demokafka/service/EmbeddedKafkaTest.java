package com.example.demokafka.service;

import com.example.demokafka.dto.UserDto;
import com.example.demokafka.values.AppValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmbeddedKafkaTest {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private UserUpdateHandlingService handlingService;

    @Test
    public void test1() {
        UserDto data = new UserDto();
        data.setUsername("test1");
        data.setAddress("test2");

        assertEquals(0, handlingService.getMockDb().size());

        kafkaProducer.sendMessage(AppValues.TOPIC, data);

        await()
                .pollInterval(Duration.ofSeconds(3))
                .atMost(10, SECONDS)
                .untilAsserted(() -> {
                    assertEquals(1, handlingService.getMockDb().size());
                    assertEquals(data, handlingService.getMockDb().get(0));
                });

    }
}