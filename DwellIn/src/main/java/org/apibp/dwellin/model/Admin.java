package org.apibp.dwellin.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "admins")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "employee_id", unique = true)
    private String employeeId;

    @Column(name = "department")
    private String department;

    @Enumerated(EnumType.STRING)
    @Column(name = "admin_role", nullable = false)
    private AdminRole adminRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AdminStatus status;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by")
    private Long createdBy;

    public enum AdminRole {
        SUPER_ADMIN,
        ADMIN,
        MODERATOR,
        SUPPORT
    }

    public enum AdminStatus {
        ACTIVE,
        INACTIVE,
        SUSPENDED,
        LOCKED
    }
}
 