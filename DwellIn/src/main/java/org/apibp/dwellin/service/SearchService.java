package org.apibp.dwellin.service;

import lombok.RequiredArgsConstructor;
import org.apibp.dwellin.model.Property;
import org.apibp.dwellin.repository.PropertyRepository;
import org.apibp.dwellin.repository.BookingRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final PropertyRepository propertyRepo;
    private final BookingRepository bookingRepo;

    public List<Property> search(
            String city,
            String state,
            String country,
            String type,
            Double minPrice,
            Double maxPrice,
            String name,
            LocalDateTime checkIn,
            LocalDateTime checkOut
    ) {
        Specification<Property> spec = Specification.where(null);

        if (city != null)
            spec = spec.and((root, q, cb) ->
                    cb.equal(cb.lower(root.get("city")), city.toLowerCase()));

        if (state != null)
            spec = spec.and((root, q, cb) ->
                    cb.equal(cb.lower(root.get("state")), state.toLowerCase()));

        if (country != null)
            spec = spec.and((root, q, cb) ->
                    cb.equal(cb.lower(root.get("country")), country.toLowerCase()));

        if (type != null)
            spec = spec.and((root, q, cb) ->
                    cb.equal(cb.lower(root.get("propertyType")), type.toLowerCase()));

        if (name != null)
            spec = spec.and((root, q, cb) ->
                    cb.like(cb.lower(root.get("propertyTitle")), "%" + name.toLowerCase() + "%"));

        if (minPrice != null && maxPrice != null)
            spec = spec.and((root, q, cb) ->
                    cb.between(root.get("pricePerNight"), minPrice, maxPrice));

        List<Property> filtered = propertyRepo.findAll(spec);

        // If no date filter: return as-is
        if (checkIn == null || checkOut == null)
            return filtered;

        // Exclude properties with overlapping bookings
        return filtered.stream()
                .filter(property ->
                        bookingRepo.findOverlappingBookings(
                                property.getId(), checkIn, checkOut
                        ).isEmpty()
                )
                .toList();
    }
}
