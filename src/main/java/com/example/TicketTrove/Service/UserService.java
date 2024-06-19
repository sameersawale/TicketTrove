package com.example.TicketTrove.Service;

import com.example.TicketTrove.DTO.RequestDTO.UserDto;
import com.example.TicketTrove.DTO.ResponseDTO.UserResponseDto;

public interface UserService {

    public UserResponseDto addUser(UserDto userDto) throws Exception;

    public UserResponseDto getUser() throws Exception;

    public UserResponseDto updateUser(int id, UserDto userDto) throws Exception;

    public String deleteUser(int id) throws Exception;
}
