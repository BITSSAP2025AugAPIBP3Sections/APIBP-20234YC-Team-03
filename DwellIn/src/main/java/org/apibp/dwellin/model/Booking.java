package org.apibp.dwellin.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

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

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate;

    @Column(name = "guest_count", nullable = false)
    private Integer guestCount;

    @Column(name = "booking_mode", nullable = false)
    private String bookingMode;   // INSTANT | REQUEST

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status", nullable = false)
    private BookingStatus bookingStatus;
    // REQUESTED | APPROVED | REJECTED | CANCELLED | CHECKED_IN | CHECKED_OUT

    // For showing that user paid to owner (not in-app payment)
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;
    // NOT_REQUIRED | PENDING | MARKED_PAID

    // Owner uploads / enters proof or transaction reference (OPTIONAL)
    @Column(name = "payment_reference")
    private String paymentReference; // UPI ref id / internal note

    @Column(name = "payment_proof_url")
    private String paymentProofUrl; // optional screenshot/PDF in S3

    @Column(name = "owner_approval_time")
    private LocalDateTime ownerApprovalTime;

    @Column(name = "user_confirmation_time")
    private LocalDateTime userConfirmationTime; // if needed

    @Column(name = "notes", length = 1000)
    private String notes;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
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
 