package com.app.librosservice.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.librosservice.models.entities.Libro;
import com.app.librosservice.services.LibroService;

@RestController
@RequestMapping("/libros")
public class LibroController {
  private final LibroService libroService;

  public LibroController(LibroService libroService) {
    this.libroService = libroService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Libro> getLibroPorId(@PathVariable Long id) {
    Libro libro = libroService.getLibroPorId(id);
    if (libro == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(libro);
  }

  @GetMapping("/buscar")
  public ResponseEntity<List<Libro>> getLibroPorTitulo(@RequestParam(required = false) String titulo,
                                                      @RequestParam(required = false) String autor,
                                                      @RequestParam(required = false) String isbn,
                                                      @RequestParam(required = false) String categoria) {
    List<Libro> libros;
    
    if (titulo != null) {
      libros = libroService.getLibroPorTitulo(titulo);
      System.out.println("titulo: " + titulo + " libros: " + libros.size());
    } else if (autor != null) {
      libros = libroService.getLibroPorAutor(autor);
    } else if (isbn != null) {
      libros = libroService.getLibroPorIsbn(isbn);
    } else if (categoria != null) {
      libros = libroService.getLibroPorCategoria(categoria);
    } else {
      return ResponseEntity.badRequest().build();
    }

    if (libros.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(libros);
  }

  @GetMapping("/volumen")
  public ResponseEntity<Libro> getLibroPorVolumenId(@RequestParam String volumenId) {
    Libro libro = libroService.getLibroPorVolumenId(volumenId);
    if (libro == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(libro);
  }

  @PostMapping("/puntuar")
  public ResponseEntity<Libro> puntuarLibro(@RequestParam Long id, @RequestBody Double puntuacion) {
    try {
      Libro libro = libroService.getLibroPorId(id);
      libro.setCalificacionPromedio((puntuacion + libro.getCalificacionPromedio()) / 2);
      libro.setCantidadCalificaciones(libro.getCantidadCalificaciones() + 1);
      libroService.guardarLibro(libro);
      return ResponseEntity.ok(libro);
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
}
