package com.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {


  @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group-id}")
  public void consume(@Payload String message) {
    log.info("Consumed message: {}", message);

    ObjectMapper objectMapper = new ObjectMapper();
    try {
      Person person = objectMapper.readValue(message, Person.class);
      log.info("Person : {}", person);
      log.info("Person clazz : {}", person.getClass());
    } catch (JsonProcessingException e) {
      log.error("Error while reading message into PersonDto", e);
    }

  }

}

