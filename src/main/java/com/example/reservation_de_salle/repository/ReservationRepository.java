package com.example.reservation_de_salle.repository;

import com.example.reservation_de_salle.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
