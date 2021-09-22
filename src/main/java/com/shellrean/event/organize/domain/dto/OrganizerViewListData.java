package com.shellrean.event.organize.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrganizerViewListData {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
}
