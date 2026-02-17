package com.duoc.transporte.core;

import java.time.Instant;

public record UbicacionHist(String patente, String lat, String lon, Instant ts) {}
