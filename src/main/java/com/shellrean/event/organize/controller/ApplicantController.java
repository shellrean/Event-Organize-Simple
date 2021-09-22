package com.shellrean.event.organize.controller;

import com.shellrean.event.organize.domain.dto.ApplicantRequestData;
import com.shellrean.event.organize.domain.dto.ApplicantViewData;
import com.shellrean.event.organize.domain.dto.ApplicantViewListData;
import com.shellrean.event.organize.domain.model.Applicant;
import com.shellrean.event.organize.service.ApplicantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/applicants")
public class ApplicantController {

    private final ApplicantService applicantService;
    private final ModelMapper modelMapper;

    @Autowired
    public ApplicantController(ApplicantService applicantService, ModelMapper modelMapper) {
        this.applicantService = applicantService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<ApplicantViewListData>> index() {
        List<Applicant> applicants = applicantService.getAllApplicant();
        List<ApplicantViewListData> applicantViewListData = applicants.stream()
                .map((applicant) -> modelMapper.map(applicant, ApplicantViewListData.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(applicantViewListData);
    }

    @GetMapping("{applicantId}")
    public ResponseEntity<ApplicantViewData> show(@PathVariable("applicantId") Long applicantId) {
        Applicant applicant = applicantService.getApplicantById(applicantId);

        return ResponseEntity.ok(modelMapper.map(applicant, ApplicantViewData.class));
    }

    @PostMapping
    public ResponseEntity<ApplicantViewData> store(@RequestBody ApplicantRequestData applicantRequest) {
        Applicant applicant = applicantService.createNewApplicant(
                modelMapper.map(applicantRequest, Applicant.class)
        );

        return new ResponseEntity<>(
                modelMapper.map(applicant, ApplicantViewData.class),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("{applicantId}")
    public void destroy(@PathVariable("applicantId") Long applicantId) {
        applicantService.deleteApplicantById(applicantId);
    }
}
