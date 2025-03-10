package com.app.lectorservice.services;

import com.app.lectorservice.models.entities.lectores.Follow;
import com.app.lectorservice.models.entities.lectores.Lector;
import com.app.lectorservice.models.repositories.FollowRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FollowService {
  private final FollowRepository followRepository;

  public FollowService(FollowRepository followRepository) {
    this.followRepository = followRepository;
  }

  public void guardarFollow(Follow follow) {
    followRepository.save(follow);
  }

  public void cancelarFollow(Long followId) {
    followRepository.deleteById(followId);
  }

  public List<Lector> getSeguidores(Long lectorId) {
    return followRepository.findByDestinoId(lectorId);
  }

  public List<Lector> getSeguidos(Long lectorId) {
    return followRepository.findByOrigenId(lectorId);
  }
  
}
