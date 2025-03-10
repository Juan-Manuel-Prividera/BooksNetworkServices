package com.app.librosservice.services.GoogleBooksApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.librosservice.models.dtos.ImageLinks;
import com.app.librosservice.models.dtos.IndustryIdentifier;
import com.app.librosservice.models.dtos.Item;
import com.app.librosservice.models.dtos.ResponseApi;
import com.app.librosservice.models.dtos.UniqueVolume;
import com.app.librosservice.models.dtos.VolumeInfo;
import com.app.librosservice.models.entities.Autor;
import com.app.librosservice.models.entities.Categoria;
import com.app.librosservice.models.entities.Imagen;
import com.app.librosservice.models.entities.Isbn;
import com.app.librosservice.models.entities.Libro;
import com.app.librosservice.models.repositories.AutorRepository;
import com.app.librosservice.models.repositories.CategoriaRepository;
import com.app.librosservice.models.repositories.ImagenRepository;
import com.app.librosservice.models.repositories.IsbnRepository;

@Service
public class GoogleBooksAdapter {
  private final GoogleBooksApi googleBooksApi;
  private final CategoriaRepository categoriaRepository;
  private final AutorRepository autorRepository;
  private final IsbnRepository isbnRepository;
  private final ImagenRepository imagenRepository;

  public GoogleBooksAdapter(GoogleBooksApi googleBooksApi,
                          CategoriaRepository categoriaRepository,
                          AutorRepository autorRepository,
                          IsbnRepository isbnRepository,
                          ImagenRepository imagenRepository) {
    this.googleBooksApi = googleBooksApi;
    this.categoriaRepository = categoriaRepository;
    this.autorRepository = autorRepository;
    this.isbnRepository = isbnRepository;
    this.imagenRepository = imagenRepository;
  }

  public List<Libro> buscarLibroPorIsbn(String isbn) {
    return convertRespone(googleBooksApi.buscarPorISBN(isbn));
  }

  public List<Libro> buscarLibroPorTitulo(String titulo) {
    return convertRespone(googleBooksApi.buscarPorTitulo(titulo));
  }

  public List<Libro> buscarLibroPorAutor(String autor) {
    return convertRespone(googleBooksApi.buscarPorAutor(autor));
  }

  public Libro buscarLibroPorVolumenId(String volumenId) {
    return convertirLibro(googleBooksApi.buscarPorVolumenId(volumenId));
  }

  public List<Libro> buscarLibroPorCategoria(String categoria) {
    return convertRespone(googleBooksApi.buscarPorCategoria(categoria));
  }

  private List<Libro> convertRespone(ResponseApi responseApi) {
    if (responseApi == null || responseApi.getItems() == null) {
        return new ArrayList<>();
    }
    return Arrays.stream(responseApi.getItems())
        .map(this::convertirLibro)
        .collect(Collectors.toList());
  }

  private Libro convertirLibro(Item item) {
    var builder = extraerDatosDeVolumeInfo(item.getVolumeInfo());
    return builder
        .volumeId(item.getId())
        .build();
  }

  private Libro convertirLibro(UniqueVolume uniqueVolume) {
    var builder = extraerDatosDeVolumeInfo(uniqueVolume.getVolumeInfo());
    return builder
        .volumeId(uniqueVolume.getId())
        .build();
  }

  private Libro.LibroBuilder extraerDatosDeVolumeInfo(VolumeInfo volumeInfo) {
    return Libro.builder()
        .titulo(volumeInfo != null ? volumeInfo.getTitle() : null)
        .autores(obtenerOCrearAutores(volumeInfo))
        .fechaPublicacion(volumeInfo != null ? volumeInfo.getPublishedDate() : null)
        .descripcion(volumeInfo != null ? volumeInfo.getDescription() : null)
        .categorias(obtenerOCrearCategorias(volumeInfo))
        .calificacionPromedio(volumeInfo != null ? volumeInfo.getAverageRating() : null)
        .cantidadCalificaciones(volumeInfo != null ? volumeInfo.getRatingsCount() : null)
        .imagenPortada(volumeInfo != null && volumeInfo.getImageLinks() != null ? 
            obtenerOCrearImagen(volumeInfo.getImageLinks()) : null)
        .idioma(volumeInfo != null ? volumeInfo.getLanguage() : null)
        .isbn(volumeInfo != null ? obtenerOCrearIsbn(volumeInfo.getIndustryIdentifiers()) : null)
        .cantidadPaginas(volumeInfo != null ? volumeInfo.getPageCount() : null);
  }

  private Imagen obtenerOCrearImagen(ImageLinks imageLinks) {
    return imagenRepository.findBySmallThumbnail(imageLinks.getSmallThumbnail())
        .orElse(new Imagen(imageLinks));
  }

  private Isbn obtenerOCrearIsbn(IndustryIdentifier[] identifiers) {
    if (identifiers == null) return null;
    
    IndustryIdentifier isbnIdentifier = null;
    
    // Buscar primero ISBN-13
    for (IndustryIdentifier identifier : identifiers) {
        if ("ISBN_13".equals(identifier.getType())) {
            isbnIdentifier = identifier;
            break;
        }
    }
    
    // Si no hay ISBN-13, tomar el primer ISBN disponible
    if (isbnIdentifier == null) {
        for (IndustryIdentifier identifier : identifiers) {
            if (identifier.getType().startsWith("ISBN")) {
                isbnIdentifier = identifier;
                break;
            }
        }
    }
    
    if (isbnIdentifier == null) return null;
    
    return isbnRepository.findByIsbn(isbnIdentifier.getIdentifier())
        .orElse(new Isbn(isbnIdentifier));
  }

  private List<Autor> obtenerOCrearAutores(VolumeInfo volumeInfo) {
    return volumeInfo != null && volumeInfo.getAuthors() != null ? 
            Arrays.stream(volumeInfo.getAuthors())
                .map(author -> {
                    try {
                        return autorRepository.findByNombreCompleto(author)
                            .orElseGet(() -> {
                                System.out.println("Creando nuevo autor: " + author);
                                return autorRepository.save(new Autor(author));
                            });
                    } catch (Exception e) {
                        // Si hay error por concurrencia, intentamos buscar de nuevo
                        return autorRepository.findByNombreCompleto(author)
                            .orElseThrow(() -> new RuntimeException("No se pudo procesar el autor: " + author));
                    }
                })
                .collect(Collectors.toList()) :
            new ArrayList<>();
  }
  private List<Categoria> obtenerOCrearCategorias(VolumeInfo volumeInfo) {
    return volumeInfo != null && volumeInfo.getCategories() != null ?
            Arrays.stream(volumeInfo.getCategories())
                .map(category -> {
                    try {
                        return categoriaRepository.findByNombreCategoria(category)
                            .orElseGet(() -> {
                                System.out.println("Creando nueva categoría: " + category);
                                return categoriaRepository.save(new Categoria(category));
                            });
                    } catch (Exception e) {
                        // Si hay error por concurrencia, intentamos buscar de nuevo
                        return categoriaRepository.findByNombreCategoria(category)
                            .orElseThrow(() -> new RuntimeException("No se pudo procesar la categoría: " + category));
                    }
                })
                .collect(Collectors.toList()) :
            new ArrayList<>();
  }
}