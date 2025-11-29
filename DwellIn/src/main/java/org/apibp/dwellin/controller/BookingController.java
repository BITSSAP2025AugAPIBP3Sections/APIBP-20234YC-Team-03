package org.apibp.dwellin.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apibp.dwellin.model.Booking;
import org.apibp.dwellin.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @Operation(summary = "Create a new hourly booking")
    @PostMapping
    public Booking createBooking(
            @RequestParam Long guestId,
            @RequestParam Long propertyId,
            @RequestParam String checkIn,
            @RequestParam String checkOut,
            @RequestParam Integer guestCount,
            @RequestParam(required = false) String notes
    ) {
        return bookingService.createBooking(
                guestId,
                propertyId,
                LocalDateTime.parse(checkIn),
                LocalDateTime.parse(checkOut),
                guestCount,
                notes
        );
    }

    @Operation(summary = "Get booking by ID")
    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable Long id) {
        return bookingService.getBooking(id);
    }

    @Operation(summary = "Get all bookings")
    @GetMapping
    public List<Booking> getAll() {
        return bookingService.getAllBookings();
    }

    @Operation(summary = "Cancel a booking")
    @PutMapping("/{id}/cancel")
    public Booking cancelBooking(@PathVariable Long id) {
        return bookingService.cancelBooking(id);
    }
}
