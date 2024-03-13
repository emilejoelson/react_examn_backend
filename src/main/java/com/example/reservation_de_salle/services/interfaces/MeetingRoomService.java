package com.example.reservation_de_salle.services.interfaces;

import com.example.reservation_de_salle.dto.request.MeetingRoomDtoRequest;
import com.example.reservation_de_salle.dto.response.MeetingRoomDtoResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetingRoomService {
    MeetingRoomDtoResponse createMeetingRoom(MeetingRoomDtoRequest meetingRoomDto);
    List<MeetingRoomDtoResponse> getAllMeetingRooms();
    List<MeetingRoomDtoResponse> getAvailableMeetingRooms(LocalDateTime startDate, LocalDateTime endDate);
}
