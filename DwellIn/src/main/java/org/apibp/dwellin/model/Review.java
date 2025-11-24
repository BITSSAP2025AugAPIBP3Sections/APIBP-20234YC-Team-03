package org.apibp.dwellin.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Column(name = "rating", nullable = false)
    private Integer rating; // 1-5

    @Column(name = "comment", length = 2000)
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "review_type", nullable = false)
    private ReviewType reviewType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReviewStatus status;

    @Column(name = "is_anonymous")
    private Boolean isAnonymous;

    @Column(name = "host_response", length = 2000)
    private String hostResponse;

    @Column(name = "host_response_date")
    private LocalDateTime hostResponseDate;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum ReviewType {
        GUEST_TO_PROPERTY,
        HOST_TO_GUEST
    }

    public enum ReviewStatus {
        ACTIVE,
        HIDDEN,
        DELETED,
        REPORTED,
        UNDER_REVIEW
    }
}
 