package com.example.TicketTrove.Repository;

import com.example.TicketTrove.Enum.ERole;
import com.example.TicketTrove.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
