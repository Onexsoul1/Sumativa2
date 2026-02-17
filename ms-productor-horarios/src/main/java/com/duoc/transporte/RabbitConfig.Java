package com.duoc.transporte;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class RabbitConfig {

  @Bean
  public Queue cola2(@Value("${app.queues.cola2}") String name) {
    return new Queue(name, true);
  }
}

