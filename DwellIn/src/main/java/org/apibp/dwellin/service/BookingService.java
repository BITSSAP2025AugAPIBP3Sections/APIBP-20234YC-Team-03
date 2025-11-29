package org.apibp.dwellin.service;

import lombok.RequiredArgsConstructor;
import org.apibp.dwellin.exception.BookingConflictException;
import org.apibp.dwellin.exception.InvalidDateException;
import org.apibp.dwellin.exception.ResourceNotFoundException;
import org.apibp.dwellin.model.Booking;
import org.apibp.dwellin.model.Property;
import org.apibp.dwellin.model.User;
import org.apibp.dwellin.repository.BookingRepository;
import org.apibp.dwellin.repository.PropertyRepository;
import org.apibp.dwellin.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepo;
    private final UserRepository userRepo;
    private final PropertyRepository propertyRepo;

    public Booking createBooking(Long guestId,
                                 Long propertyId,
                                 LocalDateTime checkIn,
                                 LocalDateTime checkOut,
                                 Integer guestCount,
                                 String notes) {

        // Validate date order
        if (checkOut.isBefore(checkIn)) {
            throw new InvalidDateException("Check-out must be after check-in time.");
        }

        // Fetch guest
        User guest = userRepo.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found"));

        // Fetch property
        Property property = propertyRepo.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        // Check overlapping bookings
        List<Booking> overlapping = bookingRepo.findOverlappingBookings(propertyId, checkIn, checkOut);
        if (!overlapping.isEmpty()) {
            throw new BookingConflictException("Selected time slot is already booked.");
        }

        // Build booking
        Booking booking = Booking.builder()
                .guest(guest)
                .property(property)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .guestCount(guestCount)
                .notes(notes)
                .bookingMode("REQUEST")                          // default
                .bookingStatus(Booking.BookingStatus.REQUESTED) // default
                .paymentStatus(Booking.PaymentStatus.PENDING)   // default
                .createdAt(LocalDateTime.now())
                .build();

        return bookingRepo.save(booking);
    }

    public Booking getBooking(Long id) {
        return bookingRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    public List<Booking> getByProperty(Long propertyId) {
        return bookingRepo.findByPropertyId(propertyId);
    }

    public Booking cancelBooking(Long bookingId) {
        Booking booking = getBooking(bookingId);
        booking.setBookingStatus(Booking.BookingStatus.CANCELLED);
        booking.setUpdatedAt(LocalDateTime.now());
        return bookingRepo.save(booking);
    }
}
