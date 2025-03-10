package com.app.lectorservice.services;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class LibroService {
  private final RestClient restClient;
  private final DiscoveryClient discoveryClient;

  public LibroService(RestClient.Builder restClientBuilder, DiscoveryClient discoveryClient) {
    this.restClient = restClientBuilder.build();
    this.discoveryClient = discoveryClient;
  }

  public int getCantidadPaginas(Long libroId) {
    ServiceInstance serviceInstance = discoveryClient.getInstances("libro-service").get(0);
    String url = serviceInstance.getUri().toString();
    Integer cantidadPaginas = restClient.get()
      .uri(url + "/libros/{id}/paginas", libroId)
      .retrieve()
      .body(Integer.class);
    if (cantidadPaginas == null) {
      throw new RuntimeException("No se encontró el libro con id " + libroId + " o no tiene dispoible la cantidad de paginas");
    }
    return cantidadPaginas;
  }

}
