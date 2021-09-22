package com.shellrean.event.organize.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(
        name = "events"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(
            nullable = false
    )
    private String location;

    @Column(
            nullable = false
    )
    private String speaker;

    @Column(
            nullable = false
    )
    private Integer seatTotal;

    @Column(
            nullable = false
    )
    private Date eventDate;

    @ManyToOne
    @JoinColumn(
            name = "organizer_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "event_organizer_id_fk"
            )
    )
    private Organizer organizer;

    @OneToMany(
            mappedBy = "event"
    )
    private List<Enrolment> enrolments = new ArrayList<>();

    public Event(String title,
                 String body,
                 String location,
                 String speaker,
                 Integer seatTotal,
                 Date eventDate,
                 Organizer organizer) {
        this.title = title;
        this.body = body;
        this.location = location;
        this.speaker = speaker;
        this.seatTotal = seatTotal;
        this.eventDate = eventDate;
        this.organizer = organizer;
    }
}
