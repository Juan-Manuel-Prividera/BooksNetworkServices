package com.app.librosservice.services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.librosservice.models.entities.Libro;
import com.app.librosservice.models.repositories.LibroRepository;
import com.app.librosservice.services.GoogleBooksApi.GoogleBooksAdapter;


@Service
public class LibroService {
  private final GoogleBooksAdapter googleBooksAdapter;
  private final LibroRepository libroRepository;
  
  public LibroService(GoogleBooksAdapter googleBooksAdapter, LibroRepository libroRepository) {
    this.googleBooksAdapter = googleBooksAdapter;
    this.libroRepository = libroRepository;
  }
  
  @Transactional
  public Libro getLibroPorId(Long id) {
    return libroRepository.findById(id).orElse(null);
  }

  @Transactional
  public List<Libro> getLibroPorTitulo(String titulo) {
    try {
      return libroRepository.findByTitulo(titulo)
        .filter(libros -> !libros.isEmpty())
        .orElseGet(() -> {
          List<Libro> libros = googleBooksAdapter.buscarLibroPorTitulo(titulo);
          return guardarLibrosSeguro(libros);
        });
    } catch (Exception e) {
      return libroRepository.findByTitulo(titulo)
        .orElseThrow(() -> new RuntimeException("No se pudieron procesar los libros para el título: " + titulo));
    }
  }

  @Transactional
  public List<Libro> getLibroPorAutor(String autor) {
    try {
      return libroRepository.findByAutor(autor)
        .filter(libros -> !libros.isEmpty())
        .orElseGet(() -> {
          List<Libro> libros = googleBooksAdapter.buscarLibroPorAutor(autor);
          return guardarLibrosSeguro(libros);
        });
    } catch (Exception e) {
      return libroRepository.findByAutor(autor)
        .orElseThrow(() -> new RuntimeException("No se pudieron procesar los libros para el autor: " + autor));
    }
  }
  @Transactional
  public List<Libro> getLibroPorIsbn(String isbn) {
    try {
      return libroRepository.findByIsbn(isbn)
        .filter(libros -> !libros.isEmpty())
        .orElseGet(() -> {
          List<Libro> libros = googleBooksAdapter.buscarLibroPorIsbn(isbn);
          return guardarLibrosSeguro(libros);
        });
    } catch (Exception e) {
      return libroRepository.findByIsbn(isbn)
        .orElseThrow(() -> new RuntimeException("No se pudieron procesar los libros para el ISBN: " + isbn));
    }
  }
  @Transactional
  public List<Libro> getLibroPorCategoria(String categoria) {
    try {
      return libroRepository.findByCategoria(categoria)
        .filter(libros -> !libros.isEmpty())
        .orElseGet(() -> {
          List<Libro> libros = googleBooksAdapter.buscarLibroPorCategoria(categoria);
          return guardarLibrosSeguro(libros);
        });
    } catch (Exception e) {
      return libroRepository.findByCategoria(categoria)
        .orElseThrow(() -> new RuntimeException("No se pudieron procesar los libros para la categoría: " + categoria));
    }
  }
  @Transactional
  public Libro getLibroPorVolumenId(String volumenId) {
    try {
      return libroRepository.findByVolumeId(volumenId)
        .orElseGet(() -> {
          Libro libro = googleBooksAdapter.buscarLibroPorVolumenId(volumenId);
          return guardarLibroSeguro(libro);
        });
    } catch (Exception e) {
      return libroRepository.findByVolumeId(volumenId)
        .orElseThrow(() -> new RuntimeException("No se pudo procesar el libro con volumeId: " + volumenId));
    }
  }
  @Transactional
  private Libro guardarLibroSeguro(Libro libro) {
    try {
      return libroRepository.findByVolumeId(libro.getVolumeId())
          .orElseGet(() -> libroRepository.save(libro));
    } catch (Exception e) {
      return libroRepository.findByVolumeId(libro.getVolumeId())
          .orElseThrow(() -> new RuntimeException("No se pudo procesar el libro con volumeId: " + libro.getVolumeId()));
    }
  }
  @Transactional
  private List<Libro> guardarLibrosSeguro(List<Libro> libros) {
    List<Libro> librosGuardados = new ArrayList<>();
    for (Libro libro : libros) {
      librosGuardados.add(guardarLibroSeguro(libro));
    }
    return librosGuardados;
  }

  // Estos métodos ya no son necesarios públicamente
  public void guardarLibro(Libro libro) {
    libroRepository.save(libro);
  }

  public void guardarLibros(List<Libro> libros) {
    libroRepository.saveAll(libros);
  }
}