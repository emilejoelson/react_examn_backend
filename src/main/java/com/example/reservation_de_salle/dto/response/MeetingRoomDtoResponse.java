package com.example.reservation_de_salle.dto.response;

import com.example.reservation_de_salle.entities.MeetingRoom;

import java.io.Serializable;

/**
 * A DTO for the {@link MeetingRoom} entity
 */
public record MeetingRoomDtoResponse(Long id, String name) implements Serializable {
}
