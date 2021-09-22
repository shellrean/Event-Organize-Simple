package com.shellrean.event.organize.repository;

import com.shellrean.event.organize.domain.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

    boolean existsByEmail(String email);
}
