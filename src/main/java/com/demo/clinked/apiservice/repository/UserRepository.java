package com.demo.clinked.apiservice.repository;

import com.demo.clinked.apiservice.data.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    List<User> findByUsername(String username);
    Optional<User> findById(UUID id);

}
