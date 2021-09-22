package com.shellrean.event.organize.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrolmentEmbed implements Serializable {

    private Long eventId;

    private Long applicantId;
}
