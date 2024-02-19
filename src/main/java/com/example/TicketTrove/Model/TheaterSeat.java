package com.example.TicketTrove.Model;

import com.example.TicketTrove.Enum.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "theaterSeat")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheaterSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "theater_id")
    private Theater theater;

}
