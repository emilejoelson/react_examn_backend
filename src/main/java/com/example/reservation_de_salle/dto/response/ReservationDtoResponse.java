package com.example.reservation_de_salle.dto.response;

import com.example.reservation_de_salle.entities.Reservation;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link Reservation} entity
 */
public record ReservationDtoResponse(Long id, LocalDateTime startDate,LocalDateTime endDate,Long meetingRoomId_target) implements Serializable {
}
