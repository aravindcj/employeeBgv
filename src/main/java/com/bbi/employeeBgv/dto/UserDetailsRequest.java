package com.bbi.employeeBgv.dto;

import com.bbi.employeeBgv.model.UserDetails;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class UserDetailsRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;

    private Long userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String currentAddress;
    private String permanentAddress;
    private String profilePicture;
    private LocalDate dob;
    @Enumerated(EnumType.STRING)
    private UserDetails.VerificationStatus verificationStatus = UserDetails.VerificationStatus.PENDING;

    List<String> skills;
}
