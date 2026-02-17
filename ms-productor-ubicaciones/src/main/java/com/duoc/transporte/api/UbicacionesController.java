package com.duoc.transporte.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ubicaciones")
public class UbicacionesController {

  private final RabbitTemplate rabbit;
  private final String cola;

  public UbicacionesController(RabbitTemplate rabbit,
                               @Value("${app.queues.cola1}") String cola) {
    this.rabbit = rabbit;
    this.cola = cola;
  }

  @PostMapping
  public String enviar(@Valid @RequestBody UbicacionMsg msg) {
    rabbit.convertAndSend(cola, msg);
    return "OK -> enviado a " + cola;
  }

  public record UbicacionMsg(
      @NotBlank String patente,
      @NotBlank String lat,
      @NotBlank String lon
  ) {}
}
