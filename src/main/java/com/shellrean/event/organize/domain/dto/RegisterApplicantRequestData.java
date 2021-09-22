package com.shellrean.event.organize.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegisterApplicantRequestData {

    @NotNull(message = "eventId is mandatory")
    private Long eventId;

    @NotNull(message = "applicantId is mandatory")
    private Long applicantId;
}
