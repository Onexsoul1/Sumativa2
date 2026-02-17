package com.duoc.transporte.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/horarios")
public class HorariosController {

  private final RabbitTemplate rabbit;
  private final String cola;

  public HorariosController(RabbitTemplate rabbit,
                            @Value("${app.queues.cola2}") String cola) {
    this.rabbit = rabbit;
    this.cola = cola;
  }

  @PostMapping
  public String enviar(@Valid @RequestBody HorarioMsg msg) {
    rabbit.convertAndSend(cola, msg);
    return "OK -> enviado a " + cola;
  }

  public record HorarioMsg(
      @NotBlank String patente,
      @NotBlank String fecha,   // "2026-02-15"
      @NotBlank String hora     // "10:30"
  ) {}
}
