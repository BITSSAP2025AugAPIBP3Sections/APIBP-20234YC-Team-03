package org.apibp.dwellin.repository;

import org.apibp.dwellin.model.Review;
import org.apibp.dwellin.model.Property;
import org.apibp.dwellin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByReviewer(User reviewer);

    List<Review> findByProperty(Property property);

    List<Review> findByRating(Integer rating);
}
