package com.example.expertmot.service;

import com.example.expertmot.domain.Meeting;
import com.example.expertmot.domain.User;
import com.example.expertmot.exceptions.ClientNotFoundException;
import com.example.expertmot.repositories.UserRepository;
import com.example.expertmot.repositories.MeetingRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MeetingRepository meetingRepository;

    public UserService(UserRepository userRepository, MeetingRepository meetingRepository) {
        this.userRepository = userRepository;
        this.meetingRepository = meetingRepository;
    }

    public String authenticateUser(User user) throws ClientNotFoundException{
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Optional<User> optionalUser = userRepository.findById(user.getId());

        if(optionalUser.isPresent()){
            User databaseUser = optionalUser.get();
            if(bCryptPasswordEncoder.matches(user.getPassword(), databaseUser.getPassword()))
                return "Authenticated User";
            else return "Incorrect Password";
        }
        throw new ClientNotFoundException(user.getId());
    }

    public User saveUser(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if(user.getPassword() == null){
            user.setPassword("default");
        }

        String encrypted = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encrypted);
        userRepository.save(user);
        return user;
    }

    public void addMeeting(Meeting meeting){
        meetingRepository.save(meeting);
    }

}
