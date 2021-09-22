package com.shellrean.event.organize.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
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

    public Organizer(String name,
                     String email,
                     String phoneNumber,
                     String address) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
