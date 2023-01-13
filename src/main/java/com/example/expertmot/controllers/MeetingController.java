package com.example.expertmot.controllers;

import com.example.expertmot.domain.Meeting;
import com.example.expertmot.exceptions.MeetingNotFoundException;
import com.example.expertmot.repositories.MeetingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
@RestController
public class MeetingController {
    private final MeetingRepository repository;

    public MeetingController(MeetingRepository repository) {this.repository = repository;}

    // Aggregate root
    // tag::get-aggregate-root[]

    @GetMapping("/meetings")
    public List<Meeting> findAll() {
        return repository.findAll();
    }

    // end::get-aggregate-root[]

    @PostMapping("/clients/{user_id}/meetings")
    public Meeting createNewMeeting(@RequestBody Meeting newMeeting) {
        return repository.save(newMeeting);
    }

    // Single item
    @GetMapping("/clients/{user_id}/meetings/{id}")
    public Meeting one(@PathVariable Long id) throws MeetingNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new MeetingNotFoundException(id));
    }

    @DeleteMapping("/clients/{user_id}/meetings/{id}")
    void deleteMeeting(@PathVariable Long id) {
        repository.deleteById(id);
    }
    @PutMapping("/clients/{user_id}/meetings/{id}")
    public Meeting updateMeeting(@RequestBody Meeting updatedMeeting, @PathVariable Long id) throws MeetingNotFoundException {
        return repository.findById(id)
                .map(meeting -> {
                    meeting.setDate(updatedMeeting.getDate());
                    meeting.setCity(updatedMeeting.getCity());
                    meeting.setStreet(updatedMeeting.getStreet());
                    meeting.setStreetNumber(updatedMeeting.getStreetNumber());
                    meeting.setHouseNumber(updatedMeeting.getHouseNumber());
                    return repository.save(meeting);
                })
                .orElseThrow(() -> new MeetingNotFoundException(id));
    }
}
