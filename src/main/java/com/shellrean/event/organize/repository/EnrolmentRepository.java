package com.shellrean.event.organize.repository;

import com.shellrean.event.organize.domain.model.Applicant;
import com.shellrean.event.organize.domain.model.Enrolment;
import com.shellrean.event.organize.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrolmentRepository extends JpaRepository<Enrolment, Long> {

    @Query("select case when (count(e) > 0)  then true else false end  \n" +
            "from Enrolment e where e.applicant.id = ?1 and e.event.id = ?2")
    boolean existsByApplicantAndEvent(Long applicant, Long event);

    List<Enrolment> findByEventIdAndAccept(Long eventId, boolean accept);
}
