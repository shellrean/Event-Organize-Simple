package com.shellrean.event.organize.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(
        name = "mst_applicants"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Applicant(String fullName, String email, String phoneNumber, String address, Date dob) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dob = dob;
    }
}
