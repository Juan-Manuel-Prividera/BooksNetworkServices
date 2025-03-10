package com.app.lectorservice.services;

import java.util.List;

import com.app.lectorservice.models.entities.lectores.Lector;
import com.app.lectorservice.models.entities.lecturas.Lectura;
import com.app.lectorservice.models.entities.lecturas.SesionDeLectura;
import com.app.lectorservice.models.repositories.LectorRepository;
import com.app.lectorservice.models.repositories.LecturaRepository;
import com.app.lectorservice.models.repositories.SesionDeLecturaRepository;

import org.springframework.stereotype.Service;

@Service
public class LectorService {
  private final LectorRepository lectorRepository;
  private final LecturaRepository lecturaRepository;
  private final SesionDeLecturaRepository sesionDeLecturaRepository;

  public LectorService(LectorRepository lectorRepository, LecturaRepository lecturaRepository, SesionDeLecturaRepository sesionDeLecturaRepository) {
    this.lectorRepository = lectorRepository;
    this.lecturaRepository = lecturaRepository;
    this.sesionDeLecturaRepository = sesionDeLecturaRepository;
  }

  public Lector getLector(Long lectorId) {
    return lectorRepository.findById(lectorId).orElse(null);
  }

  public SesionDeLectura getSesionDeLectura(Long sesionId) {
    return sesionDeLecturaRepository.findById(sesionId).orElse(null);
  }

  public Lectura getLectura(Long lecturaId) {
    return lecturaRepository.findById(lecturaId).orElse(null);
  }


  public void actualizarLectura(Lectura lectura) {
    lecturaRepository.save(lectura);
  }

  public void actualizarSesionDeLectura(SesionDeLectura sesionDeLectura) {
    sesionDeLecturaRepository.save(sesionDeLectura);
  }

  public void guardarSesionDeLectura(SesionDeLectura sesionDeLectura) {
    sesionDeLecturaRepository.save(sesionDeLectura);
  }

  public void guardarLectura(Lectura lectura) {
    lecturaRepository.save(lectura);
  }

  public List<Lectura> getLecturas(Long lectorId) {
    return lecturaRepository.findByLectorId(lectorId);
  }

  public List<SesionDeLectura> getSesionesDeLectura(Long lecturaId) {
    return sesionDeLecturaRepository.findByLecturaId(lecturaId);
  }

  public void guardarLector(Lector lector) {
    lectorRepository.save(lector);
  }

  public void actualizarLector(Lector lector) {
    lectorRepository.save(lector);
  }

  public void eliminarLector(Long lectorId) {
    lectorRepository.deleteById(lectorId);
  }
}
