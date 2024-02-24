package com.example.TicketTrove.DTO.RequestDTO;

import com.example.TicketTrove.Model.Role;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDto {

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String mobileNo;

    private String address;

    private String password;

    private Set<String> roles;
}
