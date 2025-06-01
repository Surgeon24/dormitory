package krakow.university.dormitory.controllers;

import krakow.university.dormitory.dto.NewsDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @GetMapping
    public List<NewsDTO> getNews() {
        return List.of(
                new NewsDTO("Wi-Fi outage", "Today from 13:00–14:00"),
                new NewsDTO("New ping-pong table", "Now available!")
        );
    }
}