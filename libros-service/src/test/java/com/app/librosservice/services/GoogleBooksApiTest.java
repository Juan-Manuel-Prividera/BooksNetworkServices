package com.app.librosservice.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.librosservice.models.dtos.ResponseApi;
import com.app.librosservice.models.dtos.UniqueVolume;
import com.app.librosservice.services.GoogleBooksApi.GoogleBooksApi;

@ExtendWith(MockitoExtension.class)
public class GoogleBooksApiTest {
    
    @Mock
    private GoogleBooksApi googleBooksApi;

    @Test
    public void buscarPorTitulo() {
        // Arrange
        String titulo = "Harry Potter";
        ResponseApi mockResponse = new ResponseApi();
        when(googleBooksApi.buscarPorTitulo(titulo)).thenReturn(mockResponse);

        // Act
        ResponseApi response = googleBooksApi.buscarPorTitulo(titulo);
        
        // Assert
        assertNotNull(response);
        verify(googleBooksApi).buscarPorTitulo(titulo);
    }

    @Test
    public void buscarPorAutor() {
        // Arrange
        String autor = "J.K. Rowling";
        ResponseApi mockResponse = new ResponseApi();
        when(googleBooksApi.buscarPorAutor(autor)).thenReturn(mockResponse);

        // Act
        ResponseApi response = googleBooksApi.buscarPorAutor(autor);
        
        // Assert
        assertNotNull(response);
        verify(googleBooksApi).buscarPorAutor(autor);
    }
    
    @Test
    public void buscarPorIsbn() {
        // Arrange
        String isbn = "9780553804577";
        ResponseApi mockResponse = new ResponseApi();
        when(googleBooksApi.buscarPorISBN(isbn)).thenReturn(mockResponse);

        // Act
        ResponseApi response = googleBooksApi.buscarPorISBN(isbn);
        
        // Assert
        assertNotNull(response);
        verify(googleBooksApi).buscarPorISBN(isbn);
    }

    @Test
    public void buscarPorVolumenId() {
        // Arrange
        String volumenId = "zaRoX10_UsMC";
        UniqueVolume mockResponse = new UniqueVolume();
        when(googleBooksApi.buscarPorVolumenId(volumenId)).thenReturn(mockResponse);

        // Act
        UniqueVolume response = googleBooksApi.buscarPorVolumenId(volumenId);
        
        // Assert
        assertNotNull(response);
        verify(googleBooksApi).buscarPorVolumenId(volumenId);
    }

    @Test
    public void buscarPorCategoria() {
        // Arrange
        String categoria = "Fantasía";
        ResponseApi mockResponse = new ResponseApi();
        when(googleBooksApi.buscarPorCategoria(categoria)).thenReturn(mockResponse);

        // Act
        ResponseApi response = googleBooksApi.buscarPorCategoria(categoria);
        
        // Assert
        assertNotNull(response);
        verify(googleBooksApi).buscarPorCategoria(categoria);
    }
}
