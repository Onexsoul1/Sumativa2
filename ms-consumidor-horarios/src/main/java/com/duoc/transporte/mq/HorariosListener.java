package com.duoc.transporte.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class HorariosListener {

  private final ObjectMapper mapper = new ObjectMapper();
  private final List<String> files = Collections.synchronizedList(new ArrayList<>());
  private final Path outDir;

  public HorariosListener(@Value("${app.outDir}") String outDir) throws Exception {
    this.outDir = Paths.get(outDir);
    Files.createDirectories(this.outDir);
  }

  public record HorarioMsg(String patente, String fecha, String hora) {}

  @RabbitListener(queues = "${app.queues.cola2}")
  public void onMessage(HorarioMsg msg) throws Exception {
    String name = "horario-" + msg.patente() + "-" + System.currentTimeMillis() + ".json";
    Path file = outDir.resolve(name);
    Files.writeString(file, mapper.writeValueAsString(msg), StandardOpenOption.CREATE_NEW);
    files.add(0, name);
    if (files.size() > 100) files.remove(files.size() - 1);
  }

  public List<String> listFiles() {
    return new ArrayList<>(files);
  }
}
