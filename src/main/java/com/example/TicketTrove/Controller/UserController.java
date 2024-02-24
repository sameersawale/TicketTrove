package com.example.TicketTrove.Controller;

import com.example.TicketTrove.DTO.RequestDTO.LoginRequest;
import com.example.TicketTrove.DTO.RequestDTO.UserDto;
import com.example.TicketTrove.DTO.ResponseDTO.LoginResponse;
import com.example.TicketTrove.DTO.ResponseDTO.UserResponseDto;
import com.example.TicketTrove.Model.User;
import com.example.TicketTrove.Security.JwtHelper;
import com.example.TicketTrove.Service.Impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.StringTokenizer;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private JwtHelper helper;

    @Autowired
    AuthenticationManager manager;

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @PostMapping("/add")
    public UserResponseDto addUser(@Valid @RequestBody UserDto userDto){
        try {
            return userService.addUser(userDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        this.doAuthenticate(loginRequest.getEmail(), loginRequest.getPassword());
        UserDetails userDetails=userService.loadUserByUsername(loginRequest.getEmail());
        String token=this.helper.generateToken(userDetails);

        LoginResponse response=LoginResponse.builder()
                .token(token)
                .email(userDetails.getUsername())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String username, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }


    @GetMapping("/get")
    public User getCurrentUser(){
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
