package com.bbi.employeeBgv.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;

    private String firstName;
    private String lastName;
    private String phone;
    private String currentAddress;
    private String permanentAddress;
    private String profilePicture;
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private VerificationStatus verificationStatus = VerificationStatus.PENDING;

    public enum VerificationStatus {
        PENDING, VERIFIED, REJECTED
    }

    @ManyToMany
    @JoinTable(
            name = "user_skill",
      joinColumns = @JoinColumn(name = "detail_id", referencedColumnName = "detailId"),
            inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "skillId")
    )
    private Set<Skill> skills = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
