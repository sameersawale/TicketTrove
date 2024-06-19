package com.example.TicketTrove.Controller;

import com.example.TicketTrove.DTO.RequestDTO.LoginRequest;
import com.example.TicketTrove.DTO.RequestDTO.UserDto;
import com.example.TicketTrove.DTO.ResponseDTO.LoginResponse;
import com.example.TicketTrove.DTO.ResponseDTO.UserResponseDto;
import com.example.TicketTrove.Model.Role;
import com.example.TicketTrove.Model.User;
import com.example.TicketTrove.Security.JwtHelper;
import com.example.TicketTrove.Security.Service.UserDetailsImpl;
import com.example.TicketTrove.Security.Service.UserDetailsServiceImpl;
import com.example.TicketTrove.Service.Impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;


    @Autowired
    private JwtHelper helper;

    @Autowired
    AuthenticationManager manager;

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @PostMapping("/add")
    public UserResponseDto addUser(@RequestBody UserDto userDto){
        try {
            return userService.addUser(userDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        this.doAuthenticate(loginRequest.getEmail(), loginRequest.getPassword());
        UserDetails userDetails= userDetailsService.loadUserByUsername(loginRequest.getEmail());
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

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }


    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdmin(){
        return "I am admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String getUser(){
        return "I am user";
    }

    @GetMapping("/current")
    public ResponseEntity<?> currentUser() throws Exception {
//        UserResponseDto userResponseDto=userService.getUser();
//        return new ResponseEntity<>(userResponseDto, HttpStatus.FOUND);
        UserDetailsImpl user= (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

}
