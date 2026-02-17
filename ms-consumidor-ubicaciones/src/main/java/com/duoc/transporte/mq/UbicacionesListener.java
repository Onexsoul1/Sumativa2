package com.duoc.transporte.mq;

import com.duoc.transporte.db.UbicacionEntity;
import com.duoc.transporte.db.UbicacionRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UbicacionesListener {

  private final UbicacionRepository repo;

  public UbicacionesListener(UbicacionRepository repo) {
    this.repo = repo;
  }

  @RabbitListener(queues = "${app.queues.cola1}")
  public void onMessage(UbicacionMsg msg) {
    System.out.println("[cola1] recibido -> " + msg);
    repo.save(new UbicacionEntity(msg.patente(), msg.lat(), msg.lon()));
  }

  public record UbicacionMsg(String patente, String lat, String lon) {}
}
