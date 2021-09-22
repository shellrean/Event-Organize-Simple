package com.shellrean.event.organize.controller;

import com.shellrean.event.organize.domain.dto.EventRequestData;
import com.shellrean.event.organize.domain.dto.EventViewData;
import com.shellrean.event.organize.domain.dto.EventViewListData;
import com.shellrean.event.organize.domain.dto.RegisterApplicantRequestData;
import com.shellrean.event.organize.domain.model.Applicant;
import com.shellrean.event.organize.domain.model.Enrolment;
import com.shellrean.event.organize.domain.model.Event;
import com.shellrean.event.organize.domain.model.Organizer;
import com.shellrean.event.organize.service.ApplicantService;
import com.shellrean.event.organize.service.EventService;
import com.shellrean.event.organize.service.OrganizerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/events")
public class EventController {

    private final EventService eventService;
    private final ModelMapper modelMapper;
    private final OrganizerService organizerService;
    private final ApplicantService applicantService;

    @Autowired
    public EventController(EventService eventService,
                           ModelMapper modelMapper,
                           OrganizerService organizerService,
                           ApplicantService applicantService) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;
        this.organizerService = organizerService;
        this.applicantService = applicantService;
    }

    @GetMapping
    public ResponseEntity<?> index() {
        List<Event> events = eventService.getAllEvents();

        return ResponseEntity.ok(
                events.stream().map((event) -> modelMapper.map(event, EventViewListData.class))
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity<EventViewData> store(@RequestBody @Valid EventRequestData eventRequest) {
        Organizer organizer = organizerService.findOrganizerById(eventRequest.getOrganizerId());
        Event event = modelMapper.map(eventRequest, Event.class);
        event.setOrganizer(organizer);

        EventViewData eventViewData = modelMapper.map(eventService.createNewEvent(event), EventViewData.class);

        return new ResponseEntity<>(eventViewData, HttpStatus.CREATED);
    }

    @GetMapping("{eventId}")
    public ResponseEntity<EventViewData> show(@PathVariable("eventId") Long eventId) {
        EventViewData eventViewData = modelMapper.map(eventService.findEventById(eventId), EventViewData.class);

        return ResponseEntity.ok(eventViewData);
    }

    @DeleteMapping("{eventId}")
    public void destroy(@PathVariable("eventId") Long eventId) {
        eventService.deleteEventById(eventId);
    }
}