package com.app.librosservice.services.GoogleBooksApi;

import com.app.librosservice.models.dtos.ResponseApi;
import com.app.librosservice.models.dtos.UniqueVolume;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GoogleBooksApi {
  private static final String API_KEY = "?key=AIzaSyBeKKHRHlsxmSpwbeOp8DTTl-Toum9gZL8";
  private static final String API_URL = "https://www.googleapis.com/books/v1/volumes";

  private final RestClient restClient;

  public GoogleBooksApi(RestClient.Builder restClientBuilder) {
    restClient = restClientBuilder.build();
  }

  public ResponseApi buscarPorTitulo(String titulo) {
    ResponseApi responseApi = restClient.get()
        .uri(API_URL + API_KEY +"&q=intitle:" +  titulo)
        .retrieve()
        .body(ResponseApi.class);
    return responseApi;
  }

  public ResponseApi buscarPorAutor(String autor) {
    ResponseApi responseApi = restClient.get()
        .uri(API_URL + API_KEY + "&q=inauthor:" +  autor)
        .retrieve()
        .body(ResponseApi.class);
    return responseApi;
  }

  public ResponseApi buscarPorISBN(String isbn) {
    ResponseApi responseApi = restClient.get()
        .uri(API_URL + API_KEY +"&q=isbn:" +  isbn)
        .retrieve()
        .body(ResponseApi.class);
    return responseApi;
  }

  public ResponseApi buscarPorCategoria(String categoria) {
    ResponseApi responseApi = restClient.get()
        .uri(API_URL + API_KEY + "&q=subject:" + categoria)
        .retrieve()
        .body(ResponseApi.class);
    return responseApi;
  }

  public UniqueVolume buscarPorVolumenId(String volumenId) {
    UniqueVolume responseApi = restClient.get()
        .uri(API_URL + "/" + volumenId + API_KEY)
        .retrieve()
        .body(UniqueVolume.class);
    return responseApi;
  }
}

