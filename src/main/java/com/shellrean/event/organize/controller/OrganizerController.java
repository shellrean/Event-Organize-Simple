package com.shellrean.event.organize.controller;

import com.shellrean.event.organize.domain.dto.OrganizerRequestData;
import com.shellrean.event.organize.domain.dto.OrganizerViewData;
import com.shellrean.event.organize.domain.dto.OrganizerViewListData;
import com.shellrean.event.organize.domain.model.Organizer;
import com.shellrean.event.organize.exception.ResourceNotFoundException;
import com.shellrean.event.organize.service.OrganizerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/organizers")
public class OrganizerController {
    private final OrganizerService organizerService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrganizerController(OrganizerService organizerService, ModelMapper modelMapper) {
        this.organizerService = organizerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<OrganizerViewListData>> index() {
        List<Organizer> organizers = organizerService.getAllOrganizers();

        List<OrganizerViewListData> organizersResult = organizers.stream()
                .map((organizer) -> modelMapper.map(organizer, OrganizerViewListData.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(organizersResult);
    }

    @GetMapping("{organizerId}")
    public ResponseEntity<OrganizerViewData> show(@PathVariable("organizerId") Long organizerId) {
        Organizer organizer = organizerService.findOrganizerById(organizerId);
        OrganizerViewData organizerViewData = modelMapper.map(organizer, OrganizerViewData.class);

        return ResponseEntity.ok(organizerViewData);
    }

    @PostMapping
    public ResponseEntity<? extends Object> store(@RequestBody @Valid OrganizerRequestData organizerRequest) {
        Organizer organizer = organizerService.createNewOrganizer(modelMapper.map(organizerRequest, Organizer.class));
        OrganizerViewData organizerViewData = modelMapper.map(organizer, OrganizerViewData.class);

        return new ResponseEntity<OrganizerViewData>(
                organizerViewData,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("{organizerId}")
    public void destroy(@PathVariable("organizerId") Long organizerId) {
        organizerService.deleteOrganizerById(organizerId);
    }
}
