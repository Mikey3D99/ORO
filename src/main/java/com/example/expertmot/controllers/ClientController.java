package com.example.expertmot.controllers;

import com.example.expertmot.domain.Meeting;
import com.example.expertmot.domain.User;
import com.example.expertmot.exceptions.ClientNotFoundException;
import com.example.expertmot.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
@RestController
public class ClientController {
    private final UserRepository repository;

    public ClientController(UserRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/clients")
    public List<User> findAll() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @GetMapping("/clients/{id}/meetings")
    public List<Meeting> findMeetings(@RequestParam Long userId) { return repository.findMeetings(userId);}

    @PostMapping("/clients")
    public User createNewClient(@RequestBody User newClient) {
        return repository.save(newClient);
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
