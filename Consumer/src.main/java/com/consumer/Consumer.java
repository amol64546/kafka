package com.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication(scanBasePackages =    "com.aidtaas.mobius.kafka.wrapper.lib.kafka.consumer")
@EnableKafka
public class Consumer {

  public static void main(String[] args) {
    SpringApplication.run(Consumer.class, args);
  }

}