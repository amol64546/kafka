package com.producer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class KafkaProducer {

  @Autowired
  private KafkaTemplate kafkaTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  @Value("${kafka.topic}")
  private String kafkaTopic;

  @PostMapping("/produce")
  public void produce(@RequestBody Person person) {
    log.info("Person : {}", person);
    log.info("Person clazz : {}", person.getClass());

    String jsonString = null;
    try {
      jsonString = objectMapper.writeValueAsString(person);
    } catch (JsonProcessingException e) {
     log.error("Error writing json to string", e);
    }

    kafkaTemplate.send(kafkaTopic, jsonString);
  }


}

