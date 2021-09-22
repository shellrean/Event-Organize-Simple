package com.shellrean.event.organize.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "enrolments"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrolment {

    @EmbeddedId
    private EnrolmentEmbed id;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(
            name = "event_id",
            foreignKey = @ForeignKey(
                    name = "enrolment_event_id_fk"
            )
    )
    @JsonBackReference
    private Event event;

    @ManyToOne
    @MapsId("applicantId")
    @JoinColumn(
            name = "applicant_id",
            foreignKey = @ForeignKey(
                    name = "enrolment_applicant_id_fk"
            )
    )
    @JsonBackReference
    private Applicant applicant;

    @Column(
            nullable = false
    )
    private Boolean accept;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Enrolment(Event event, Applicant applicant) {
        this.event = event;
        this.applicant = applicant;
    }
}
