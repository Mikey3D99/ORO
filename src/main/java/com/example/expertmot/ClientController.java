package com.example.expertmot;

import com.example.expertmot.User;
import com.example.expertmot.ClientNotFoundException;
import com.example.expertmot.UserRepository;
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

}
