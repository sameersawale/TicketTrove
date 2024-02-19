package com.example.TicketTrove.Service.Impl;

import com.example.TicketTrove.DTO.RequestDTO.ScreenDto;
import com.example.TicketTrove.DTO.ResponseDTO.ScreenResponseDto;
import com.example.TicketTrove.Enum.SeatType;
import com.example.TicketTrove.Model.*;
import com.example.TicketTrove.Repository.MovieRepository;
import com.example.TicketTrove.Repository.ScreenRepository;
import com.example.TicketTrove.Repository.TheaterRepository;
import com.example.TicketTrove.Service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScreenServiceImpl implements ScreenService {

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    MovieRepository movieRepository;
    @Override
    public ScreenResponseDto addScreen(ScreenDto screenDto) throws Exception {
        Theater theater= theaterRepository.findById(screenDto.getTheaterId()).get();
        Movie movie=movieRepository.findById(screenDto.getMovieId()).get();

        Screen screen= Screen.builder()
                .showTime(screenDto.getShowTime())
                .showDate(screenDto.getShowDate())
                .theater(theater)
                .movie(movie)
                .build();
        List<ShowSeat> showSeatList=createShowSeat(screenDto, screen);
        screen.setShowSeatList(showSeatList);
        screenRepository.save(screen);

        List<Screen>screenList=movie.getScreenList();
        screenList.add(screen);
        movie.setScreenList(screenList);
        movieRepository.save(movie);

        List<Screen> screens=theater.getScreenList();
        screens.add(screen);
        theater.setScreenList(screens);
        theaterRepository.save(theater);

        ScreenResponseDto screenResponseDto=ScreenResponseDto.builder()
                .showTime(screen.getShowTime())
                .showDate(screen.getShowDate())
                .movieName(screen.getMovie().getMovieName())
                .theaterName(screen.getTheater().getName())
                .build();
        return screenResponseDto;
    }

    @Override
    public ScreenResponseDto updateScreen(ScreenDto screenDto) throws Exception {
        return null;
    }

    @Override
    public ScreenResponseDto getById(int id) throws Exception {
        Screen screen=screenRepository.findById(id).get();
        ScreenResponseDto screenResponseDto=ScreenResponseDto.builder()
                .showTime(screen.getShowTime())
                .showDate(screen.getShowDate())
                .theaterName(screen.getTheater().getName())
                .movieName(screen.getMovie().getMovieName())
                .build();
        return screenResponseDto;
    }

    @Override
    public String deleteById(int id) throws Exception {
        Screen screen=screenRepository.findById(id).get();
        Theater theater=screen.getTheater();
        List<Screen>screens=theater.getScreenList();
        screens.remove(screen);
        theater.setScreenList(screens);
        theaterRepository.save(theater);

        Movie movie=screen.getMovie();
        List<Screen>screenList=movie.getScreenList();
        screenList.remove(screen);
        theater.setScreenList(screenList);
        movieRepository.save(movie);

        screenRepository.delete(screen);

        return "screen deleted successfully....";
    }

    public List<ShowSeat>createShowSeat(ScreenDto showDto, Screen show){
        Theater theater=show.getTheater();

        List<TheaterSeat> theaterSeatList=theater.getTheaterSeatList();

        List<ShowSeat> showSeatList=new ArrayList<>();

        for (TheaterSeat theaterSeat:theaterSeatList){
            ShowSeat showSeat=new ShowSeat();
            showSeat.setSeatNo(theaterSeat.getSeatNo());
            showSeat.setSeatType(theaterSeat.getSeatType());

            if(showSeat.getSeatType().equals(SeatType.CLASSIC))
                showSeat.setPrice(showDto.getClassicSeatPrice());
            else
                showSeat.setPrice(showDto.getExecutiveSeatPrice());

            showSeat.setBooked(false);
            showSeat.setScreen(show);

            showSeatList.add(showSeat);
        }
        return showSeatList;
    }

}
