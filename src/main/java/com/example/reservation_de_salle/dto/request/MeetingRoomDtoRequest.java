package com.example.reservation_de_salle.dto.request;

import com.example.reservation_de_salle.entities.MeetingRoom;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link MeetingRoom} entity
 */
public record MeetingRoomDtoRequest(
        @NotNull(message = "ID cannot be null")
        Long id,
        @NotNull(message = "Name is required")
        @NotBlank(message = "Name should not be blank")
        @Size(min = 3, message = "Name should contain at least {min} characters")
        String name
) implements Serializable { }