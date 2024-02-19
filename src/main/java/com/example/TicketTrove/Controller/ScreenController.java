package com.example.TicketTrove.Controller;

import com.example.TicketTrove.DTO.RequestDTO.ScreenDto;
import com.example.TicketTrove.DTO.ResponseDTO.ScreenResponseDto;
import com.example.TicketTrove.Model.Screen;
import com.example.TicketTrove.Service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/screen")
public class ScreenController {

    @Autowired
    ScreenService screenService;

    @PostMapping("/add")
    public ScreenResponseDto addShow(@RequestBody ScreenDto screenDto){
        try {
            return screenService.addScreen(screenDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get/{id}")
    public ScreenResponseDto getById(@PathVariable("id") int id){
        try {
            return screenService.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        try {
            return screenService.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("error while deleting screen please check show id");
        }
    }
}
