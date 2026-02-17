package com.duoc.transporte.db;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "ubicaciones")
public class UbicacionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String patente;

  @Column(nullable = false)
  private String lat;

  @Column(nullable = false)
  private String lon;

  @Column(nullable = false)
  private LocalDateTime fecha;

  // JPA requiere constructor vacío
  public UbicacionEntity() {}

  // ✅ el que necesitas en el listener
  public UbicacionEntity(String patente, String lat, String lon) {
    this.patente = patente;
    this.lat = lat;
    this.lon = lon;
    this.fecha = LocalDateTime.now();
  }

  // getters/setters mínimos
  public Long getId() { return id; }
  public String getPatente() { return patente; }
  public String getLat() { return lat; }
  public String getLon() { return lon; }
  public LocalDateTime getFecha() { return fecha; }

  public void setId(Long id) { this.id = id; }
  public void setPatente(String patente) { this.patente = patente; }
  public void setLat(String lat) { this.lat = lat; }
  public void setLon(String lon) { this.lon = lon; }
  public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
