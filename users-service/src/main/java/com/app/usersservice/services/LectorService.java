package com.app.usersservice.services;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.client.RestClient;

import com.app.usersservice.models.dtos.LectorDTO;

public class LectorService {
  private final DiscoveryClient discoveryClient;
  private final RestClient restClient;

  public LectorService(DiscoveryClient discoveryClient, RestClient restClient) {
    this.discoveryClient = discoveryClient;
    this.restClient = restClient;
  }

  public void sendToLectorService(LectorDTO lectorDTO) {
    ServiceInstance lectorServiceInstance = discoveryClient.getInstances("lector-service").get(0);
    String lectorServiceUrl = lectorServiceInstance.getUri().toString();
    restClient.post()
      .uri(lectorServiceUrl + "/lectores")
      .body(lectorDTO)
      .retrieve()
      .body(LectorDTO.class);
  }
}
