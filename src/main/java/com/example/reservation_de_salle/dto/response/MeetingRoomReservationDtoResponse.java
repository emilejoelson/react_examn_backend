package com.example.reservation_de_salle.dto.response;
import com.example.reservation_de_salle.entities.MeetingRoom;
import com.example.reservation_de_salle.entities.Reservation;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * A DTO for the {@link MeetingRoom} entity
 */
public record MeetingRoomReservationDtoResponse(Set<ReservationDto> reservations) implements Serializable {
    /**
     * A DTO for the {@link Reservation} entity
     */
    public record ReservationDto(int id, LocalDateTime startDate,LocalDateTime endDate) implements Serializable{

    }
}


