package com.example.TicketTrove.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theater")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String address;

    private String city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "theater", cascade = CascadeType.ALL)
    private List<Screen>screenList=new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "theater", cascade = CascadeType.ALL)
    private List<TheaterSeat>theaterSeatList=new ArrayList<>();


}
