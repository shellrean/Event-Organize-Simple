package com.shellrean.event.organize.domain.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class EnrolmentEmbed {

    private Long eventId;

    private Long applicantId;
}
