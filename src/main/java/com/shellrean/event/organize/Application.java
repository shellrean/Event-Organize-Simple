package com.shellrean.event.organize;

import com.shellrean.event.organize.domain.model.Organizer;
import com.shellrean.event.organize.repository.OrganizerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(OrganizerRepository organizerRepository) {
        return (args) -> {
            Organizer organizer = new Organizer(
                    "shellrean",
                    "info@shellrean.com",
                    "0893-23232-3232",
                    "Yogyakarta"
            );
            organizerRepository.save(organizer);
        };
    }
}
