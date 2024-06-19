package com.example.TicketTrove.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "screen")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date showDate;

    private LocalTime showTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "screen", cascade = CascadeType.ALL)
    private List<ShowSeat> showSeatList=new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "screen", cascade = CascadeType.ALL)
    private List<Ticket>ticketList=new ArrayList<>();

}
