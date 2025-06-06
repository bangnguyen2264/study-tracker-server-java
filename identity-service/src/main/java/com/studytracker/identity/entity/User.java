package com.studytracker.identity.entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "username", unique = true, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String username;

    String password;

    @Column(name = "email", unique = true, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String email;

    @Column(name = "email_verified", nullable = false, columnDefinition = "boolean default false")
    boolean emailVerified;

    @Column(name = "active", columnDefinition = "TINYINT(1) DEFAULT 1")
    boolean active;

    @ManyToMany
    Set<Role> roles;

    @Column(name = "dob")
    LocalDate dob;

    @Column(name = "occupation", columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String occupation;
}
