package com.shellrean.event.organize.service;

import com.shellrean.event.organize.domain.model.Event;
import com.shellrean.event.organize.exception.ResourceNotFoundException;
import com.shellrean.event.organize.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final static String NOT_FOUND_MSG = "event with id %d not found";

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        if(events.isEmpty()) {
            throw new ResourceNotFoundException("there is no data found");
        }

        return events;
    }

    public Event findEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MSG, eventId)));
    }

    public Event createNewEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEventById(Long eventId) {
        boolean eventExists = eventRepository.existsById(eventId);
        if(!eventExists) {
            throw new ResourceNotFoundException(String.format(NOT_FOUND_MSG, eventId));
        }

        eventRepository.deleteById(eventId);
    }
}
