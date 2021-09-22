package com.shellrean.event.organize.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "mst_organizers",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "organizer_email_unique",
                        columnNames = "email"
                )
        }
)
@Data
public class Organizer {

    @Id
    @SequenceGenerator(
            name = "organizer_id_sequence",
            sequenceName = "organizer_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "organizer_id_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            nullable = false
    )
    private String name;

    @Column(
            nullable = false,
            length = 150
    )
    private String email;

    @Column(
            nullable = false
    )
    private String phoneNumber;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String address;

    @OneToMany(
            mappedBy = "organizer",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Event> events = new ArrayList<>();
}
