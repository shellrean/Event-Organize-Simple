package com.shellrean.event.organize.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shellrean.event.organize.domain.model.Organizer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class EventRequestData {
    private Long id;

    @NotBlank(message = "title is mandatory")
    private String title;

    private String body;

    @NotBlank(message = "location is mandatory")
    private String location;

    @NotBlank(message = "speaker is mandatory")
    private String speaker;

    @Positive(message = "setTotal must be positive")
    private Integer seatTotal;

    @NotNull(message = "organizerId is mandatory")
    private Long organizerId;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd hh:mm"
    )
    @NotNull
    private Date eventDate;
}
