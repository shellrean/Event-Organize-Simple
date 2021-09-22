package com.shellrean.event.organize.domain.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class EnrolmentEmbed implements Serializable {

    private Long eventId;

    private Long applicantId;
}
