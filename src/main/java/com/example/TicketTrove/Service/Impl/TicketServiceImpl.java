package com.example.TicketTrove.Service.Impl;

import com.example.TicketTrove.DTO.RequestDTO.TicketDto;
import com.example.TicketTrove.DTO.ResponseDTO.ScreenResponseDto;
import com.example.TicketTrove.DTO.ResponseDTO.TicketResponseDto;
import com.example.TicketTrove.Model.*;
import com.example.TicketTrove.Repository.ScreenRepository;
import com.example.TicketTrove.Repository.TicketRepository;
import com.example.TicketTrove.Repository.UserRepository;
import com.example.TicketTrove.Security.Service.UserDetailsImpl;
import com.example.TicketTrove.Service.ScreenService;
import com.example.TicketTrove.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public TicketResponseDto bookTicket(TicketDto ticketDto) {
        Ticket ticket=new Ticket();

        boolean isValidRequest=checkValidityOfRequestedSeats(ticketDto);
        if(!isValidRequest){
            throw new RuntimeException("Requested seats are not available.");
        }

//        calculate the amount of ticket
        int amount=0;

        Screen screen=screenRepository.findById(ticketDto.getShowId()).get();
        List<ShowSeat>showSeatList=screen.getShowSeatList();
        List<String> requestedSeats=ticketDto.getRequestedSeats();

        for(ShowSeat showSeat:showSeatList){
            if(requestedSeats.contains(showSeat.getSeatNo())){
                amount+=showSeat.getPrice();
                showSeat.setBooked(true);
                showSeat.setBookedAt(new Date());
            }
        }
        ticket.setTotalAmount(amount);
        ticket.setMovieName(screen.getMovie().getMovieName());
        ticket.setTheaterName(screen.getTheater().getName());
        ticket.setShowDate(screen.getShowDate());
        ticket.setShowTime(screen.getShowTime());

        String allowedSeats=getAllAllocatedSeatsFromShowSeats(requestedSeats);
        ticket.setBookedSeats(allowedSeats);

        UserDetailsImpl userDetails= (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email= userDetails.getEmail();
        User user=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("user not found"));
        ticket.setUser(user);
        ticket.setScreen(screen);

        ticketRepository.save(ticket);

        List<Ticket>ticketList=screen.getTicketList();
        ticketList.add(ticket);
        screen.setTicketList(ticketList);

        screenRepository.save(screen);

        List<Ticket>ticketList1=user.getTicketList();
        ticketList1.add(ticket);
        user.setTicketList(ticketList1);

        userRepository.save(user);

        TicketResponseDto ticketResponseDto=entityToDto(ticket);
        ticketResponseDto.setUsername(user.getFirstName() +" "+ user.getLastName());
        ticketResponseDto.setMessage("Thank you for choosing ticketTrove!!");

        return ticketResponseDto;
    }


    private boolean checkValidityOfRequestedSeats(TicketDto ticketDto){
        List<String>requestedSeats=ticketDto.getRequestedSeats();

        Screen screen=screenRepository.findById(ticketDto.getShowId()).get();

        List<ShowSeat>listOfSeats=screen.getShowSeatList();

        for(ShowSeat showSeat:listOfSeats){
            String seatNo=showSeat.getSeatNo();

            if(requestedSeats.contains(seatNo)){
                if(showSeat.isBooked()){
                    return false;
                }
            }
        }
        return true;
    }

    private String getAllAllocatedSeatsFromShowSeats(List<String>requestedSeats){
        StringBuilder result= new StringBuilder();

        for(String seat:requestedSeats){
            result.append(seat).append(" ");
        }
        return result.toString();
    }

    private TicketResponseDto entityToDto(Ticket ticket){
        TicketResponseDto ticketResponseDto=TicketResponseDto.builder()
                .ticketId(ticket.getTicketId())
                .seats(ticket.getBookedSeats())
                .showTime(ticket.getShowTime())
                .showDate(ticket.getShowDate())
                .theaterName(ticket.getTheaterName())
                .username(ticket.getMovieName())
                .totalPrice(ticket.getTotalAmount())
                .build();
        return ticketResponseDto;
    }

    @Override
    public List<TicketResponseDto> listOfAllTickets(int userId) {
        return null;
    }
}
