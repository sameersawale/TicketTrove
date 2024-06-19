package com.example.TicketTrove.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ticket")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ticketId= UUID.randomUUID().toString();

    private String movieName;

    private Date showDate;

    private LocalTime showTime;

    private int totalAmount;

    private String theaterName;

    private String bookedSeats;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "screen_id")
    private Screen screen;
}
