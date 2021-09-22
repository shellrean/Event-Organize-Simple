package com.shellrean.event.organize.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrganizerRequestData {
    private Long id;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "email is mandatory")
    private String email;

    @NotBlank(message = "phoneNumber is mandatory")
    private String phoneNumber;

    @NotBlank(message = "address is mandatory")
    private String address;
}
