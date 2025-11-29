package org.apibp.dwellin.controller;

import lombok.RequiredArgsConstructor;
import org.apibp.dwellin.model.Property;
import org.apibp.dwellin.service.SearchService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public List<Property> search(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String checkIn,
            @RequestParam(required = false) String checkOut
    ) {
        return searchService.search(
                city,
                state,
                country,
                type,
                minPrice,
                maxPrice,
                name,
                checkIn != null ? LocalDateTime.parse(checkIn) : null,
                checkOut != null ? LocalDateTime.parse(checkOut) : null
        );
    }
}
