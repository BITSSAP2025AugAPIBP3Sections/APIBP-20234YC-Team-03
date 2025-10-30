package org.apibp.dwellin.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Owner / Host Information
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    // Personal & Business Information
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "preferred_name")
    private String preferredName;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "business_registration_number")
    private String businessRegistrationNumber;

    @Column(name = "business_register_authority")
    private String businessRegisterAuthority;

    @Column(name = "contact_address", nullable = false)
    private String contactAddress;

    @Column(name = "contact_phone", nullable = false)
    private String contactPhone;

    @Column(name = "contact_email", nullable = false)
    private String contactEmail;

    // Government / Legal Compliance
    @Column(name = "local_permit_required")
    private Boolean localPermitRequired;

    @Column(name = "local_permit_details")
    private String localPermitDetails;

    @Column(name = "tourism_dept_registered")
    private Boolean tourismDeptRegistered;

    @Column(name = "commercial_license_required")
    private Boolean commercialLicenseRequired;

    @Column(name = "gst_applicable")
    private Boolean gstApplicable;

    @Column(name = "gst_number")
    private String gstNumber;

    // PDF Document URLs / Keys
    @Column(name = "noc_panchayat_pdf_url")
    private String nocPanchayatPdfUrl;

    @Column(name = "fire_noc_pdf_url")
    private String fireNocPdfUrl;

    @Column(name = "tourism_registration_pdf_url")
    private String tourismRegistrationPdfUrl;

    @Column(name = "commercial_license_pdf_url")
    private String commercialLicensePdfUrl;

    @Column(name = "gst_certificate_pdf_url")
    private String gstCertificatePdfUrl;

    // Property Details
    @Column(name = "property_title", nullable = false)
    private String propertyTitle;

    @Column(name = "property_address", nullable = false)
    private String propertyAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "property_type")
    private String propertyType;

    @Column(name = "number_of_rooms")
    private Integer numberOfRooms;

    @Column(name = "max_guests")
    private Integer maxGuests;

    // Safety
    @Column(name = "has_smoke_detector")
    private Boolean hasSmokeDetector;

    @Column(name = "has_fire_extinguisher")
    private Boolean hasFireExtinguisher;

    @Column(name = "meets_safety_standards")
    private Boolean meetsSafetyStandards;

    // Listing Info
    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "verified")
    private Boolean verified;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "listing_created_date")
    private LocalDate listingCreatedDate;
}
