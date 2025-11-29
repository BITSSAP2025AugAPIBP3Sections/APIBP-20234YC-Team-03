package org.apibp.dwellin.controller;

import lombok.RequiredArgsConstructor;
import org.apibp.dwellin.model.Review;
import org.apibp.dwellin.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{reviewerId}/property/{propertyId}/booking/{bookingId}")
    public Review createReview(
            @PathVariable Long reviewerId,
            @PathVariable Long propertyId,
            @PathVariable Long bookingId,
            @RequestBody Review review
    ) {
        return reviewService.createReview(reviewerId, propertyId, bookingId, review);
    }

    @GetMapping("/property/{propertyId}")
    public List<Review> getReviewsForProperty(@PathVariable Long propertyId) {
        return reviewService.getReviewsForProperty(propertyId);
    }
}
