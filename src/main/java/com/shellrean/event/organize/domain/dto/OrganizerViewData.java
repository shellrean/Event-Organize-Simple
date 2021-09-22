package com.shellrean.event.organize.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrganizerViewData {
    private Long id;
    private String name;
    private String email;

    private String phoneNumber;

    private String address;

    private List<EventViewListData> events;
}
