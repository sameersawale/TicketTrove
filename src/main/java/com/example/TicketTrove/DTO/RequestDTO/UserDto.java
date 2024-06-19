package com.example.TicketTrove.DTO.RequestDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDto {

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    private String mobileNo;

    private String address;

    private String password;

    private Set<String> roles;
}
