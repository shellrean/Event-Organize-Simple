package com.shellrean.event.organize.service;

import com.shellrean.event.organize.domain.model.Applicant;
import com.shellrean.event.organize.exception.EmailTakenException;
import com.shellrean.event.organize.exception.ResourceNotFoundException;
import com.shellrean.event.organize.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantService {

    private final static String NOT_FOUND_MSG = "applicant with id %d not found";

    private final ApplicantRepository applicantRepository;

    @Autowired
    public ApplicantService(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    public List<Applicant> getAllApplicant() {
        List<Applicant> applicants = applicantRepository.findAll();
        if (applicants.isEmpty()) {
            throw new ResourceNotFoundException("there is no data");
        }

        return applicants;
    }

    public Applicant createNewApplicant(Applicant applicant) {
        boolean existApplicant = applicantRepository.existsByEmail(applicant.getEmail());
        if (existApplicant) {
            throw new EmailTakenException(String.format("email (%s) already taken", applicant.getEmail()));
        }

        return applicantRepository.save(applicant);
    }

    public Applicant getApplicantById(Long applicantId) {
        return applicantRepository.findById(applicantId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MSG, applicantId)));
    }

    public void deleteApplicantById(Long applicantId) {
        boolean existApplicant = applicantRepository.existsById(applicantId);
        if (!existApplicant) {
            throw new ResourceNotFoundException(String.format(NOT_FOUND_MSG, applicantId));
        }

        applicantRepository.deleteById(applicantId);
    }
}
