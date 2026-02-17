package com.duoc.transporte.core;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class UbicacionMemoryRepo {
  private final CopyOnWriteArrayList<UbicacionHist> data = new CopyOnWriteArrayList<>();
  public void add(UbicacionHist u) { data.add(u); }
  public List<UbicacionHist> all() { return List.copyOf(data); }
}
