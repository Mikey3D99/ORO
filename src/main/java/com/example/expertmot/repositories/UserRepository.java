package com.example.expertmot.repositories;

import com.example.expertmot.domain.Meeting;
import com.example.expertmot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    //List<ClientsEntity> findByPublished(boolean published);

    @Query("SELECT meetings FROM User meetings WHERE meetings.id = ?1")
    List<Meeting> findMeetings(Long clientId);
}