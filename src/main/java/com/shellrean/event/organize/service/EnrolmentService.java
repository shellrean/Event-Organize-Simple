package com.shellrean.event.organize.service;

import com.shellrean.event.organize.domain.model.Applicant;
import com.shellrean.event.organize.domain.model.Enrolment;
import com.shellrean.event.organize.domain.model.EnrolmentEmbed;
import com.shellrean.event.organize.domain.model.Event;
import com.shellrean.event.organize.exception.DuplicateDataException;
import com.shellrean.event.organize.exception.ResourceNotFoundException;
import com.shellrean.event.organize.repository.ApplicantRepository;
import com.shellrean.event.organize.repository.EnrolmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrolmentService {
    private final EnrolmentRepository enrolmentRepository;

    @Autowired
    public EnrolmentService(EnrolmentRepository enrolmentRepository) {
        this.enrolmentRepository = enrolmentRepository;
    }

    public List<Enrolment> getAllAplicantOfEventId(Long eventId) {
        List<Enrolment> enrolls = enrolmentRepository.findByEventIdAndAccept(eventId, true);
        if (enrolls.isEmpty()) {
            throw new ResourceNotFoundException("there is no data");
        }
        return enrolls;
    }

    public Enrolment registerNewEnrolment(Enrolment enrolment) {
        Optional<Applicant> applicantOpt = Optional.ofNullable(enrolment.getApplicant());
        Optional<Event> eventOpt = Optional.ofNullable(enrolment.getEvent());

        Long applicantId = applicantOpt.orElseThrow(() -> new RuntimeException("applicant must present")).getId();
        Long eventId = eventOpt.orElseThrow(() -> new RuntimeException("event must present")).getId();

        boolean existEnrolment = enrolmentRepository.existsByApplicantAndEvent(applicantId, eventId);

        if (existEnrolment) {
            throw new DuplicateDataException("applicant already registered before");
        }
        enrolment.setId(new EnrolmentEmbed(eventId, applicantId));
        enrolment.setAccept(true);

        return enrolmentRepository.save(enrolment);
    }
}
