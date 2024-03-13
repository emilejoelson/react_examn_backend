package com.example.reservation_de_salle.services.implementation;

import com.example.reservation_de_salle.Exception.AppException;
import com.example.reservation_de_salle.dto.request.MeetingRoomDtoRequest;
import com.example.reservation_de_salle.dto.response.MeetingRoomDtoResponse;
import com.example.reservation_de_salle.entities.MeetingRoom;
import com.example.reservation_de_salle.entities.Reservation;
import com.example.reservation_de_salle.mapper.MeetingRoomMapper;
import com.example.reservation_de_salle.repository.MeetingRoomRepository;
import com.example.reservation_de_salle.services.interfaces.MeetingRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingRoomServiceImpl implements MeetingRoomService {
    private  final MeetingRoomRepository meetingRoomRepository;
    @Override
    public MeetingRoomDtoResponse createMeetingRoom(MeetingRoomDtoRequest meetingRoomDto) {
        // Check if room already exist
        if(meetingRoomRepository.existsById(meetingRoomDto.id())){
            throw new AppException(HttpStatus.BAD_REQUEST,"Room already exist");
        }
        //Dto to entity
        MeetingRoom meetingRoom = MeetingRoomMapper.INSTANCE.meetingRoomRequestDtoToMeetingRoom(meetingRoomDto);
        //Save in database
        MeetingRoom savedMeetingRoom = meetingRoomRepository.save(meetingRoom);
        return MeetingRoomMapper.INSTANCE.meetingRoomToMeetingRoomDtoResponse(savedMeetingRoom);
    }

    @Override
    public List<MeetingRoomDtoResponse> getAllMeetingRooms() {
        return meetingRoomRepository.findAll().stream()
                .map(MeetingRoomMapper.INSTANCE::meetingRoomToMeetingRoomDtoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoomDtoResponse> getAvailableMeetingRooms(LocalDateTime startDate, LocalDateTime endDate) {
        List<MeetingRoom> allMeetingRooms = meetingRoomRepository.findAll();
        List<MeetingRoomDtoResponse> availableMeetingRooms = new ArrayList<>();

        for (MeetingRoom room : allMeetingRooms) {
            if (isRoomAvailable(room, startDate, endDate)) {
                availableMeetingRooms.add(MeetingRoomMapper.INSTANCE.meetingRoomToMeetingRoomDtoResponse(room));
            }
        }

        return availableMeetingRooms;
    }

    private boolean isRoomAvailable(MeetingRoom room, LocalDateTime startDate, LocalDateTime endDate) {
        List<Reservation> reservations = room.getReservations();
        for (Reservation reservation : reservations) {
            LocalDateTime reservationStartTime = reservation.getStartDate();
            LocalDateTime reservationEndTime = reservation.getEndDate();
            if (reservationStartTime.isBefore(endDate) && reservationEndTime.isAfter(startDate)) {
                return false;
            }
        }
        return true;
    }

}
