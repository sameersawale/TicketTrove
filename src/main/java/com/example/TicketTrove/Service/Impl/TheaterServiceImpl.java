package com.example.TicketTrove.Service.Impl;

import com.example.TicketTrove.DTO.RequestDTO.TheaterDto;
import com.example.TicketTrove.DTO.ResponseDTO.TheaterResponseDto;
import com.example.TicketTrove.Enum.SeatType;
import com.example.TicketTrove.Model.Movie;
import com.example.TicketTrove.Model.Screen;
import com.example.TicketTrove.Model.Theater;
import com.example.TicketTrove.Model.TheaterSeat;
import com.example.TicketTrove.Repository.TheaterRepository;
import com.example.TicketTrove.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterServiceImpl implements TheaterService {

    @Autowired
    TheaterRepository theaterRepository;


    @Override
    public TheaterResponseDto addTheater(TheaterDto theaterDto) throws Exception {
        Theater theater=Theater.builder()
                .name(theaterDto.getName())
                .address(theaterDto.getAddress())
                .city(theaterDto.getCity())
                .build();
        List<TheaterSeat>theaterSeatList=createTheaterSeats(theaterDto, theater);
        theater.setTheaterSeatList(theaterSeatList);
        theaterRepository.save(theater);

        TheaterResponseDto responseDto=TheaterResponseDto.builder()
                .name(theater.getName())
                .address(theater.getAddress())
                .city(theater.getCity())
                .build();
        return responseDto;
    }

    @Override
    public TheaterResponseDto getTheaterById(int id) throws Exception {
        Theater theater=theaterRepository.findById(id).get();
        TheaterResponseDto responseDto=TheaterResponseDto.builder()
                .name(theater.getName())
                .address(theater.getAddress())
                .city(theater.getCity())
                .build();
        return responseDto;
    }

    @Override
    public TheaterResponseDto updateTheater(int id, TheaterDto theaterDto) throws Exception {
        Theater theater=theaterRepository.findById(id).get();
        theater.setName(theaterDto.getName());
        theater.setAddress(theater.getAddress());
        theater.setCity(theater.getCity());
        List<TheaterSeat>theaterSeatList=createTheaterSeats(theaterDto, theater);
        theater.setTheaterSeatList(theaterSeatList);
        theaterRepository.save(theater);

        TheaterResponseDto responseDto=TheaterResponseDto.builder()
                .name(theater.getName())
                .address(theater.getAddress())
                .city(theater.getCity())
                .build();
        return responseDto;
    }

    @Override
    public String deleteTheater(int id) throws Exception {
        theaterRepository.deleteById(id);
        return "Theater deleted successfully.....";
    }

    @Override
    public List<Screen> getAllShowsOfTheater(String name) throws Exception {
        Theater theater=theaterRepository.findByName(name).orElseThrow(()->
                new RuntimeException("please check theater name!!"));
        List<Screen> screenList=theater.getScreenList();
        return screenList;

    }

    private List<TheaterSeat> createTheaterSeats(TheaterDto theaterDto, Theater theater){
        int noOfClassicSeats= theaterDto.getClassicSeatsCount();

        int noOfPremiumSeats= theaterDto.getExecutiveSeatsCount();

        List<TheaterSeat>theaterSeatList=new ArrayList<>();

        for(int count=1; count<=noOfClassicSeats; count++){
            TheaterSeat theaterSeat=TheaterSeat.builder()
                    .seatType(SeatType.CLASSIC).seatNo(count+"C").theater(theater)
                    .build();
            theaterSeatList.add(theaterSeat);
        }

        for (int count=1; count<=noOfPremiumSeats; count++){
            TheaterSeat theaterSeat=TheaterSeat.builder()
                    .seatType(SeatType.EXECUTIVE).seatNo(count+"E").theater(theater)
                    .build();
            theaterSeatList.add(theaterSeat);
        }
        return theaterSeatList;
    }
}
