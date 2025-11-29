package org.apibp.dwellin.service;

import lombok.RequiredArgsConstructor;
import org.apibp.dwellin.exception.PropertyDeletionException;
import org.apibp.dwellin.exception.ResourceNotFoundException;
import org.apibp.dwellin.model.Property;
import org.apibp.dwellin.model.User;
import org.apibp.dwellin.model.Booking.BookingStatus;
import org.apibp.dwellin.repository.BookingRepository;
import org.apibp.dwellin.repository.PropertyRepository;
import org.apibp.dwellin.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepo;
    private final UserRepository userRepo;
    private final BookingRepository bookingRepo;

    // CREATE
    public Property createProperty(Long ownerId, Property property) {

        User owner = userRepo.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found"));

        property.setOwner(owner);
        return propertyRepo.save(property);
    }

    // UPDATE
    public Property updateProperty(Long id, Property updated) {
        Property existing = propertyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        updated.setId(existing.getId());
        updated.setOwner(existing.getOwner());

        return propertyRepo.save(updated);
    }

    public Property getProperty(Long id) {
        return propertyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));
    }

    public List<Property> getByOwner(Long ownerId) {
        User owner = userRepo.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found"));

        return propertyRepo.findByOwner(owner);
    }

    public List<Property> getAll() {
        return propertyRepo.findAll();
    }

    // DELETION PROTECTION
    public void deleteProperty(Long propertyId) {

        boolean hasActive =
                bookingRepo.existsByPropertyIdAndBookingStatusIn(
                        propertyId,
                        List.of(
                                BookingStatus.REQUESTED,
                                BookingStatus.APPROVED,
                                BookingStatus.CHECK_IN_PENDING,
                                BookingStatus.CHECKED_IN
                        )
                );

        if (hasActive) {
            throw new PropertyDeletionException("Cannot delete property — active bookings exist.");
        }

        if (!propertyRepo.existsById(propertyId)) {
            throw new ResourceNotFoundException("Property not found");
        }

        propertyRepo.deleteById(propertyId);
    }
}
