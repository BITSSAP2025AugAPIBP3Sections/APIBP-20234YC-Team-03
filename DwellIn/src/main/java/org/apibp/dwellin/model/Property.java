package org.apibp.dwellin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    // Personal & Business Info
    @Column(nullable = false)
    private String fullName;

    private String preferredName;
    private String businessName;
    private String businessRegistrationNumber;
    private String businessRegisterAuthority;

    @Column(nullable = false)
    private String contactAddress;

    @Column(nullable = false)
    private String contactPhone;

    @Column(nullable = false)
    private String contactEmail;

    // Legal Compliance
    private Boolean localPermitRequired;
    private String localPermitDetails;

    private Boolean tourismDeptRegistered;
    private Boolean commercialLicenseRequired;
    private Boolean gstApplicable;
    private String gstNumber;

    // Documents
    private String nocPanchayatPdfUrl;
    private String fireNocPdfUrl;
    private String tourismRegistrationPdfUrl;
    private String commercialLicensePdfUrl;
    private String gstCertificatePdfUrl;

    // Property Details
    @Column(nullable = false)
    private String propertyTitle;

    @Column(nullable = false)
    private String propertyAddress;

    private String city;
    private String state;
    private String country;
    private String pinCode;

    private String propertyType;
    private Integer numberOfRooms;
    private Integer maxGuests;

    private Boolean hasSmokeDetector;
    private Boolean hasFireExtinguisher;
    private Boolean meetsSafetyStandards;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private Double pricePerNight;

    private Boolean verified;
    private Boolean active;

    private LocalDate listingCreatedDate;
}
