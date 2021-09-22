package com.shellrean.event.organize;

import com.shellrean.event.organize.domain.model.Event;
import com.shellrean.event.organize.domain.model.Organizer;
import com.shellrean.event.organize.repository.EventRepository;
import com.shellrean.event.organize.repository.OrganizerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    CommandLineRunner commandLineRunner(OrganizerRepository organizerRepository,
                                        EventRepository eventRepository) {
        return (args) -> {
            Organizer organizer = new Organizer(
                    "shellrean",
                    "info@shellrean.com",
                    "0893-23232-3232",
                    "Yogyakarta"
            );
            organizer = organizerRepository.save(organizer);

            Event event = new Event(
                    "Roadmap to Java Master",
                    "mastering java programming language",
                    "Main Hall, Jakarta",
                    "Shellrean",
                    1000,
                    new Date(new Date().getTime()+7),
                    organizer
            );

            event = eventRepository.save(event);
        };
    }
}
