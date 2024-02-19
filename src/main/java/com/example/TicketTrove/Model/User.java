package com.example.TicketTrove.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String mobileNo;

    private String address;
}
