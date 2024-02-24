package com.example.TicketTrove.Service.Impl;

import com.example.TicketTrove.DTO.RequestDTO.UserDto;
import com.example.TicketTrove.DTO.ResponseDTO.UserResponseDto;
import com.example.TicketTrove.Enum.ERole;
import com.example.TicketTrove.Model.Role;
import com.example.TicketTrove.Model.User;
import com.example.TicketTrove.Repository.RoleRepository;
import com.example.TicketTrove.Repository.UserRepository;
import com.example.TicketTrove.Service.UserService;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserResponseDto addUser(UserDto userDto) throws Exception {
        User user=User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .address(userDto.getAddress())
                .mobileNo(userDto.getMobileNo())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();

        Set<String>strRoles=userDto.getRoles();
        Set<Role>roles=new HashSet<>();


            strRoles.forEach(role->{
                if (role.equals("ADMIN")) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() ->
                            new RuntimeException("Error role not found"));
                    roles.add(adminRole);
                } else if(role.equals("USER")){
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() ->
                            new RuntimeException("Error role not found"));
                    roles.add(userRole);
                }
            });

        user.setRoles(roles);

        userRepository.save(user);

        UserResponseDto userResponseDto=entityToDto(user);
        return userResponseDto;
    }

    @Override
    public UserResponseDto getUser(int id) throws Exception {
        User user=userRepository.findById(id).orElseThrow(()->new RuntimeException("please check user id!!!"));
        UserResponseDto userResponseDto=entityToDto(user);
        return userResponseDto;
    }

    @Override
    public UserResponseDto updateUser(int id, UserDto userDto) throws Exception {
        return null;
    }

    @Override
    public String deleteUser(int id) throws Exception {
        userRepository.deleteById(id);
        return "user deleted successfully.....";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("email id not found"));
    }

    public  UserResponseDto entityToDto(User user){
        UserResponseDto userResponseDto=UserResponseDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .email(user.getEmail())
                .mobileNo(user.getMobileNo())
                .roles(user.getRoles())
                .build();
        return userResponseDto;
    }
}
