package com.example.TicketTrove.Repository;

import com.example.TicketTrove.Model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {

    Optional<Theater>findByName(String name);
}
