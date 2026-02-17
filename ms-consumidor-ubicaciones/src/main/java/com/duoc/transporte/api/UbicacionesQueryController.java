package com.duoc.transporte.api;

import java.util.List;

import com.duoc.transporte.db.UbicacionEntity;
import com.duoc.transporte.db.UbicacionRepository;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ubicaciones")
public class UbicacionesQueryController {

  private final UbicacionRepository repo;

  public UbicacionesQueryController(UbicacionRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  public List<UbicacionEntity> listar() {
    return repo.findAll();
  }
}

