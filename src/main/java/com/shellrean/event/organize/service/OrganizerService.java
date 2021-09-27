package com.shellrean.event.organize.service;

import com.shellrean.event.organize.domain.model.Organizer;
import com.shellrean.event.organize.exception.EmailTakenException;
import com.shellrean.event.organize.exception.ResourceNotFoundException;
import com.shellrean.event.organize.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizerService {

    private static final String NOT_FOUND_MSG = "organizer with id %d not found";

    private final OrganizerRepository organizerRepository;

    @Autowired
    public OrganizerService(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    public List<Organizer> getAllOrganizers() {
        List<Organizer> organizers = organizerRepository.findAll();
        if (organizers.isEmpty()) {
            throw new ResourceNotFoundException("there is no data found");
        }

        return organizers;
    }

    public Organizer findOrganizerById(Long organizerId) {
        return organizerRepository.findById(organizerId).orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MSG, organizerId)));
    }

    public Organizer createNewOrganizer(Organizer organizer) {
        boolean existEmail = organizerRepository.existsByEmail(organizer.getEmail());
        if (existEmail) {
            throw new EmailTakenException(String.format("email %s already taken", organizer.getEmail()));
        }
        return organizerRepository.save(organizer);
    }

    public void deleteOrganizerById(Long organizerId) {
        boolean existOrganizer = organizerRepository.existsById(organizerId);
        if (!existOrganizer) {
            throw new ResourceNotFoundException(String.format(NOT_FOUND_MSG, organizerId));
        }

        organizerRepository.deleteById(organizerId);
    }
}
