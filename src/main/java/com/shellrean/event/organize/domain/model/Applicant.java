package com.shellrean.event.organize.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(
        name = "mst_applicants"
)
@Data
public class Applicant {

    @Id
    @SequenceGenerator(
            name = "applicant_id_sequence",
            sequenceName = "applicant_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "applicant_id_sequence"
    )
    private Long id;

    @Column(
            nullable = false
    )
    private String fullName;

    @Column(
            nullable = false,
            length = 120
    )
    private String email;

    @Column(
            nullable = false
    )
    private String phoneNumber;

    @Column(
            columnDefinition = "TEXT"
    )
    private String address;

    @Column(
            nullable = false
    )
    private Date dob;

    @OneToMany(
            mappedBy = "applicant",
            fetch = FetchType.LAZY
    )
    private List<Enrolment> enrolments = new ArrayList<>();
}
