package com.shellrean.event.organize.controller;

import com.shellrean.event.organize.domain.model.Organizer;
import com.shellrean.event.organize.exception.ResourceNotFoundException;
import com.shellrean.event.organize.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/organizers")
public class OrganizerController {
    private final OrganizerService organizerService;

    @Autowired
    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @GetMapping
    public ResponseEntity<List<Organizer>> index() {
        List<Organizer> organizers = organizerService.getAllOrganizers();

        if (organizers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(organizers);
    }

    @GetMapping("{organizerId}")
    public ResponseEntity<Organizer> show(@PathVariable("organizerId") Long organizerId) {
        return ResponseEntity.ok(organizerService.findOrganizerById(organizerId));
    }

    @PostMapping
    public ResponseEntity<? extends Object> store(@RequestBody Organizer organizer) {
        try {
            return new ResponseEntity<Organizer>(organizerService.createNewOrganizer(organizer), HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{organizerId}")
    public void destroy(@PathVariable("organizerId") Long organizerId) {
        organizerService.deleteOrganizerById(organizerId);
    }
}
