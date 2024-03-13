package com.example.reservation_de_salle.dto.request;

import com.example.reservation_de_salle.entities.Reservation;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link Reservation} entity
 */
public record ReservationDtoRequest(
        @NotNull(message = "ID cannot be null")
        Long id,
        @NotNull(message = "Start date cannot be null")
        @FutureOrPresent(message = "Start date must be in the future or present")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid date format. Date should be in yyyy-MM-dd format.")
        LocalDateTime startDate,
        @NotNull(message = "End date cannot be null")
        @Future(message = "End date must be in the future")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid date format. Date should be in yyyy-MM-dd format.")
        LocalDateTime endDate,
        Long meetingRoomId
) implements Serializable {

}
