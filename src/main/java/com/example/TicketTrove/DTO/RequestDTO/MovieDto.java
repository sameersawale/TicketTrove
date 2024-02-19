package com.example.TicketTrove.DTO.RequestDTO;

import com.example.TicketTrove.Enum.MovieGenre;
import com.example.TicketTrove.Enum.MovieLanguage;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieDto {
    private String movieName;

    private double rating;

    private int duration;

    private MovieLanguage movieLanguage;

    private MovieGenre movieGenre;
}
