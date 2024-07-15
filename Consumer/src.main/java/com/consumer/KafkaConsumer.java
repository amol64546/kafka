package com.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

  @KafkaListener(topics = "construct-data",
    containerFactory = "mappingpayloadKafkaListenerContainerFactory")
  public void receive(@Payload String msg, final @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
    final @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
    final @Header(KafkaHeaders.OFFSET) int offset, Acknowledgment ack) {
    log.info("Received message: {} from topic: {} partition: {} offset: {}", msg, topic, partition,
      offset);
  }

}

