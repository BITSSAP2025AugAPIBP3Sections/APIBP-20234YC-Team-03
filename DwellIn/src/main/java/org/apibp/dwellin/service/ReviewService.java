package org.apibp.dwellin.service;

import lombok.RequiredArgsConstructor;
import org.apibp.dwellin.exception.ResourceNotFoundException;
import org.apibp.dwellin.model.*;
import org.apibp.dwellin.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepo;
    private final UserRepository userRepo;
    private final PropertyRepository propertyRepo;
    private final BookingRepository bookingRepo;

    public Review createReview(Long reviewerId, Long propertyId, Long bookingId, Review review) {

        User reviewer = userRepo.findById(reviewerId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Property property = propertyRepo.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        review.setReviewer(reviewer);
        review.setProperty(property);
        review.setBooking(booking);
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());

        return reviewRepo.save(review);
    }

    public List<Review> getReviewsForProperty(Long propertyId) {
        Property property = propertyRepo.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        return reviewRepo.findByProperty(property);
    }
}
