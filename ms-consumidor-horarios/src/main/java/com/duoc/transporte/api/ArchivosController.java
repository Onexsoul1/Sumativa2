package com.duoc.transporte.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/archivos")
public class ArchivosController {

  private final Path outDir;

  public ArchivosController(@Value("${app.outDir}") String outDir) {
    this.outDir = Paths.get(outDir);
  }

  @GetMapping
  public List<String> listar() throws Exception {
    if (!Files.exists(outDir)) return List.of();
    try (var stream = Files.list(outDir)) {
      return stream
          .filter(Files::isRegularFile)
          .map(p -> p.getFileName().toString())
          .sorted()
          .collect(Collectors.toList());
    }
  }

  @GetMapping("/{nombre}")
  public ResponseEntity<String> leer(@PathVariable String nombre) throws Exception {
    Path file = outDir.resolve(nombre).normalize();
    if (!file.startsWith(outDir) || !Files.exists(file)) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(Files.readString(file));
  }
}
