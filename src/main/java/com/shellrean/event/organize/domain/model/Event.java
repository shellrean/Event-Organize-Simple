package com.shellrean.event.organize.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "events"
)
@Data
public class Event {

    @Id
    @SequenceGenerator(
            name = "event_id_sequence",
            sequenceName = "event_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_id_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            nullable = false
    )
    private String title;

    @Column(
            columnDefinition = "TEXT"
    )
    private String body;

    private String location;

    private String speaker;

    @Column(
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime eventDate;

    @ManyToOne
    @JoinColumn(
            name = "event_id",
            referencedColumnName = "id"
    )
    private Organizer organizer;

    @OneToMany(
            mappedBy = "event"
    )
    private List<Enrolment> enrolments = new ArrayList<>();
}
