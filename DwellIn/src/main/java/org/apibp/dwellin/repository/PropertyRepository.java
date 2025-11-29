package org.apibp.dwellin.repository;

import org.apibp.dwellin.model.Property;
import org.apibp.dwellin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PropertyRepository
        extends JpaRepository<Property, Long>, JpaSpecificationExecutor<Property> {

    List<Property> findByOwner(User owner);

    List<Property> findByCityIgnoreCase(String city);

    List<Property> findByStateIgnoreCase(String state);

    List<Property> findByCountryIgnoreCase(String country);

    List<Property> findByPricePerNightBetween(Double min, Double max);

    List<Property> findByPropertyTypeIgnoreCase(String type);
}
