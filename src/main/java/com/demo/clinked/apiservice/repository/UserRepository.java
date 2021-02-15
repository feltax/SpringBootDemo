package com.demo.clinked.apiservice.repository;

import com.demo.clinked.apiservice.data.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findById(UUID id);
   UserEntity findByUsername(String Username);
    List<UserEntity> findAll();

}
