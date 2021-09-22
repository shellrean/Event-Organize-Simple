package com.shellrean.event.organize.repository;

import com.shellrean.event.organize.domain.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    boolean existsByEmail(String email);
}
