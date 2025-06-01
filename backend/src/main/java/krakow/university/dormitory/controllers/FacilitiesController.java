package krakow.university.dormitory.controllers;

import krakow.university.dormitory.dto.FacilityDTO;
import krakow.university.dormitory.entities.Room;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/facilities")
public class FacilitiesController {

    @GetMapping
    public List<FacilityDTO> getFacilities() {
        return List.of(
                new FacilityDTO("Laundry", "Available"),
                new FacilityDTO("TV Room", "Busy")
        );
    }
}