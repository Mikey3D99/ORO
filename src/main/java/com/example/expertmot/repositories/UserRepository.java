package com.example.expertmot.repositories;

import com.example.expertmot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //List<ClientsEntity> findByPublished(boolean published);
}