package com.duoc.transporte.services;


import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class UbicacionesStore {

  private final List<Ubicacion> data = new CopyOnWriteArrayList<>();

  public void add(Ubicacion u) { data.add(u); }
  public List<Ubicacion> all() { return data; }

  public record Ubicacion(String patente, String lat, String lon, Instant recibidoEn) {}
}

