package com.example.TicketTrove.Controller;

import com.example.TicketTrove.DTO.RequestDTO.MovieDto;
import com.example.TicketTrove.DTO.ResponseDTO.MovieResponseDto;
import com.example.TicketTrove.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public MovieResponseDto addMovie(@RequestBody MovieDto movieDto){
        try {
            return movieService.addMovie(movieDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get/{id}")
    public MovieResponseDto getMovie(@PathVariable("id") int id){
        try {
            return movieService.getMovie(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public MovieResponseDto updateMovie(@PathVariable("id") int id, @RequestBody MovieDto movieDto) throws Exception {
        return movieService.updateMovie(id, movieDto);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteMovie(@PathVariable("id") int id){
        try {
            return movieService.deleteMovie(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
