package com.example.expertmot.controllers;

import com.example.expertmot.domain.Meeting;
import com.example.expertmot.domain.User;
import com.example.expertmot.exceptions.ClientNotFoundException;
import com.example.expertmot.repositories.MeetingRepository;
import com.example.expertmot.repositories.UserRepository;
import com.example.expertmot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
@RestController
public class ClientController {
    private final UserRepository repository;
    final UserService userService;

    public ClientController(UserRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/clients")
    public List<User> findAll() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @GetMapping("/clients/{id}/meetings")
    public List<Meeting> findMeetings(@PathVariable("id") User user_id) { return repository.findMeetings(user_id);}

    @PostMapping("/clients/{id}/meetings")
    public void postMeeting(@RequestBody Meeting newMeeting, @PathVariable("id") User user_id){
        Meeting meeting = new Meeting(newMeeting.getDate(), newMeeting.getCity(), newMeeting.getStreet(), newMeeting.getStreetNumber(), newMeeting.getHouseNumber(), user_id);
        userService.addMeeting(meeting);
    }

    @PostMapping("/clients")
    public User createNewClient(@RequestBody User newClient) {return userService.saveUser(newClient);}

    @PostMapping("/authenticateUsers")
    public String authenticateUser(@RequestBody User user) throws ClientNotFoundException {
        return userService.authenticateUser(user);
    }

    // Single item
    @GetMapping("/clients/{id}")
    public User one(@PathVariable Long id) throws ClientNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    @DeleteMapping("/clients/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
    @PutMapping("/clients/{id}")
    public User updateClient(@RequestBody User updatedClient, @PathVariable Long id) throws ClientNotFoundException {
        return repository.findById(id)
                .map(client -> {
                    client.setFirstName(updatedClient.getFirstName());
                    client.setLastName(updatedClient.getLastName());
                    client.setEmail(updatedClient.getEmail());
                    client.setAge(updatedClient.getAge());
                    return repository.save(client);
                })
                .orElseThrow(() -> new ClientNotFoundException(id));
    }
}
