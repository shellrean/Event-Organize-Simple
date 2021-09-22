package com.shellrean.event.organize.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ApplicantViewListData {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd"
    )
    private Date dob;


}
