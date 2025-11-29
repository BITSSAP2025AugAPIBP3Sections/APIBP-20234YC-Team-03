package org.apibp.dwellin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", nullable = false)
    private User guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @Column(nullable = false)
    private LocalDateTime checkIn;

    @Column(nullable = false)
    private LocalDateTime checkOut;

    @Column(nullable = false)
    private Integer guestCount;

    @Column(nullable = false)
    private String bookingMode; // INSTANT | REQUEST

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus bookingStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    private String paymentReference;
    private String paymentProofUrl;

    private LocalDateTime ownerApprovalTime;
    private LocalDateTime userConfirmationTime;

    @Column(length = 1000)
    private String notes;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public enum BookingStatus {
        REQUESTED,
        APPROVED,
        REJECTED,
        CANCELLED,
        CHECK_IN_PENDING,
        CHECKED_IN,
        CHECKED_OUT,
        NO_SHOW
    }

    public enum PaymentStatus {
        NOT_REQUIRED,
        PENDING,
        MARKED_PAID
    }
}
