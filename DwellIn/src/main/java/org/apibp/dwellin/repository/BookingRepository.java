package org.apibp.dwellin.repository;

import org.apibp.dwellin.model.Booking;
import org.apibp.dwellin.model.Booking.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("""
        SELECT b FROM Booking b
        WHERE b.property.id = :propertyId
        AND b.bookingStatus IN ('REQUESTED','APPROVED','CHECK_IN_PENDING','CHECKED_IN')
        AND (:checkIn < b.checkOut AND :checkOut > b.checkIn)
    """)
    List<Booking> findOverlappingBookings(
            Long propertyId,
            LocalDateTime checkIn,
            LocalDateTime checkOut
    );

    boolean existsByPropertyIdAndBookingStatusIn(Long propertyId,
                                                 List<BookingStatus> statuses);

    List<Booking> findByPropertyId(Long propertyId);
}
