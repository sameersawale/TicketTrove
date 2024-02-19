package com.example.TicketTrove.Controller;

import com.example.TicketTrove.DTO.RequestDTO.TheaterDto;
import com.example.TicketTrove.DTO.ResponseDTO.TheaterResponseDto;
import com.example.TicketTrove.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/add")
    public TheaterResponseDto addTheater(@RequestBody TheaterDto theaterDto){
        try {
            return theaterService.addTheater(theaterDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get/{id}")
    public TheaterResponseDto getTheater(@PathVariable("id")int id) throws Exception {
        return theaterService.getTheaterById(id);
    }

    @PutMapping("/update/{id}")
    public TheaterResponseDto updateTheater(@PathVariable("id")int id, @RequestBody TheaterDto theaterDto){
        try {
            return theaterService.updateTheater(id, theaterDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTheater(@PathVariable("id")int id){
        try {
            return theaterService.deleteTheater(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
