package com.shellrean.event.organize.controller;

import com.shellrean.event.organize.domain.dto.ApplicantViewListData;
import com.shellrean.event.organize.domain.dto.EnrolmentViewData;
import com.shellrean.event.organize.domain.dto.RegisterApplicantRequestData;
import com.shellrean.event.organize.domain.model.Applicant;
import com.shellrean.event.organize.domain.model.Enrolment;
import com.shellrean.event.organize.domain.model.Event;
import com.shellrean.event.organize.service.ApplicantService;
import com.shellrean.event.organize.service.EnrolmentService;
import com.shellrean.event.organize.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/enrolment")
public class EnrolmentController {

    private final EventService eventService;
    private final ApplicantService applicantService;
    private final EnrolmentService enrolmentService;
    private final ModelMapper modelMapper;

    @Autowired
    public EnrolmentController(EventService eventService, ApplicantService applicantService, EnrolmentService enrolmentService, ModelMapper modelMapper) {
        this.eventService = eventService;
        this.applicantService = applicantService;
        this.enrolmentService = enrolmentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("event/{eventId}")
    public  ResponseEntity<?> applicantEvent(@PathVariable("eventId") Long eventId) {
        List<Enrolment> enrolments = enrolmentService.getAllAplicantOfEventId(eventId);

        List<ApplicantViewListData> applicants = enrolments.stream().map(enrolment ->
                modelMapper.map(enrolment.getApplicant(), ApplicantViewListData.class)
        ).collect(Collectors.toList());

        return ResponseEntity.ok(applicants);
    }

    @PostMapping
    public ResponseEntity<?> registerApplicant(@RequestBody RegisterApplicantRequestData requestData) {
        Event event = eventService.findEventById(requestData.getEventId());
        Applicant applicant = applicantService.getApplicantById(requestData.getApplicantId());

        Enrolment enrolment = enrolmentService.registerNewEnrolment(new Enrolment(event, applicant));

        return new ResponseEntity<>(modelMapper.map(enrolment, EnrolmentViewData.class), HttpStatus.CREATED);
    }
}
