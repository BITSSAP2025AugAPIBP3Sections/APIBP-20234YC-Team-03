package org.apibp.dwellin.model;

import java.time.LocalDate;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "users")
@Data                        // Getters, Setters, toString, equals & hashCode
@NoArgsConstructor           // Default constructor
@AllArgsConstructor          // All-args constructor
@Builder                     // Builder pattern
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String phoneNo;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String mail;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private String password;

    private String address;
}
