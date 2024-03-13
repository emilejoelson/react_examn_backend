package com.example.reservation_de_salle.controller;

import com.example.reservation_de_salle.dto.request.MeetingRoomDtoRequest;
import com.example.reservation_de_salle.dto.response.MeetingRoomDtoResponse;
import com.example.reservation_de_salle.services.interfaces.MeetingRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meeting_rooms")
@CrossOrigin("*")
public class MeetingRoomController {
    private final MeetingRoomService meetingRoomService;

    @PostMapping
    public ResponseEntity<MeetingRoomDtoResponse> createMeetingRoom(@Valid @RequestBody MeetingRoomDtoRequest meetingRoomDto) {
        MeetingRoomDtoResponse response = meetingRoomService.createMeetingRoom(meetingRoomDto);
        return  ResponseEntity.created(URI.create("/meeting_rooms/"+response.id()))
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<MeetingRoomDtoResponse>> getAllMeetingRooms() {
        List<MeetingRoomDtoResponse> response = meetingRoomService.getAllMeetingRooms();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/between_two_date")
    public ResponseEntity<List<MeetingRoomDtoResponse>> getAvailableMeetingRoomsBetweenDates(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endDate
    ) {
        List<MeetingRoomDtoResponse> response = meetingRoomService.getAvailableMeetingRooms(startDate, endDate);
        return ResponseEntity.ok(response);
    }

}
