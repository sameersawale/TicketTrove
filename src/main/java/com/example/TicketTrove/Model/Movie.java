package com.example.TicketTrove.Model;

import com.example.TicketTrove.Enum.MovieGenre;
import com.example.TicketTrove.Enum.MovieLanguage;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String movieName;

    private double rating;

    private int duration;

    @Enumerated(value = EnumType.STRING)
    private MovieLanguage movieLanguage;

    @Enumerated(value = EnumType.STRING)
    private MovieGenre movieGenre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Screen> screenList=new ArrayList<>();


}
