package com.app.librosservice.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.librosservice.models.dtos.ResponseApi;
import com.app.librosservice.models.dtos.UniqueVolume;
import com.app.librosservice.models.entities.Libro;
import com.app.librosservice.services.GoogleBooksApi.GoogleBooksAdapter;
import com.app.librosservice.services.GoogleBooksApi.GoogleBooksApi;

@ExtendWith(MockitoExtension.class)
public class GoogleBooksAdapterTest {

    @Mock
    private GoogleBooksApi googleBooksApi;

    @InjectMocks
    private GoogleBooksAdapter googleBooksAdapter;

    @Test
    public void buscarLibroPorTitulo() {
        // Arrange
        String titulo = "Harry Potter";
        ResponseApi mockResponse = new ResponseApi(); // Configura con datos de prueba
        when(googleBooksApi.buscarPorTitulo(titulo)).thenReturn(mockResponse);

        // Act
        List<Libro> libros = googleBooksAdapter.buscarLibroPorTitulo(titulo);
        
        // Assert
        assertNotNull(libros);
        verify(googleBooksApi).buscarPorTitulo(titulo);
    }

    @Test
    public void buscarLibroPorAutor() {
        // Arrange
        String autor = "J.K. Rowling";
        ResponseApi mockResponse = new ResponseApi();
        when(googleBooksApi.buscarPorAutor(autor)).thenReturn(mockResponse);

        // Act
        List<Libro> libros = googleBooksAdapter.buscarLibroPorAutor(autor);
        
        // Assert
        assertNotNull(libros);
        verify(googleBooksApi).buscarPorAutor(autor);
    }

    @Test
    public void buscarLibroPorIsbn() {
        // Arrange
        String isbn = "9780553804577";
        ResponseApi mockResponse = new ResponseApi();
        when(googleBooksApi.buscarPorISBN(isbn)).thenReturn(mockResponse);

        // Act
        List<Libro> libros = googleBooksAdapter.buscarLibroPorIsbn(isbn);
        
        // Assert
        assertNotNull(libros);
        verify(googleBooksApi).buscarPorISBN(isbn);
    }

    @Test
    public void buscarLibroPorVolumenId() {
        // Arrange
        String volumenId = "zaRoX10_UsMC";
        UniqueVolume mockResponse = new UniqueVolume();
        when(googleBooksApi.buscarPorVolumenId(volumenId)).thenReturn(mockResponse);

        // Act
        Libro libro = googleBooksAdapter.buscarLibroPorVolumenId(volumenId);
        
        // Assert
        assertNotNull(libro);
        verify(googleBooksApi).buscarPorVolumenId(volumenId);
    }

    @Test
    public void buscarLibroPorCategoria() {
        // Arrange
        String categoria = "Fantasy";
        ResponseApi mockResponse = new ResponseApi();
        when(googleBooksApi.buscarPorCategoria(categoria)).thenReturn(mockResponse);

        // Act
        List<Libro> libros = googleBooksAdapter.buscarLibroPorCategoria(categoria);
        
        // Assert
        assertNotNull(libros);
        verify(googleBooksApi).buscarPorCategoria(categoria);
    }
}
