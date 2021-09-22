package com.shellrean.event.organize.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class EventViewListData {
    private Long id;
    private String title;
    private String body;
    private String location;
    private String speaker;
    private Integer seatTotal;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd hh:mm"
    )
    private Date eventDate;
}
