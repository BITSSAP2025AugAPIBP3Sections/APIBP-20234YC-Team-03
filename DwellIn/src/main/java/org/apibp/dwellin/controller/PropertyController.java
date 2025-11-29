package org.apibp.dwellin.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apibp.dwellin.model.Property;
import org.apibp.dwellin.service.PropertyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @Operation(summary = "Create a new property for an owner")
    @PostMapping("/owner/{ownerId}")
    public Property createProperty(@PathVariable Long ownerId, @RequestBody Property property) {
        return propertyService.createProperty(ownerId, property);
    }

    @Operation(summary = "Update a property")
    @PutMapping("/{id}")
    public Property updateProperty(@PathVariable Long id, @RequestBody Property property) {
        return propertyService.updateProperty(id, property);
    }

    @Operation(summary = "Get property by ID")
    @GetMapping("/{id}")
    public Property getProperty(@PathVariable Long id) {
        return propertyService.getProperty(id);
    }

    @Operation(summary = "Get properties of a specific owner")
    @GetMapping("/owner/{ownerId}")
    public List<Property> getByOwner(@PathVariable Long ownerId) {
        return propertyService.getByOwner(ownerId);
    }

    @Operation(summary = "Get all properties")
    @GetMapping
    public List<Property> getAll() {
        return propertyService.getAll();
    }

    @Operation(summary = "Delete property (not allowed if active bookings exist)")
    @DeleteMapping("/{id}")
    public String deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return "Property deleted successfully";
    }
}
