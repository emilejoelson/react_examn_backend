package com.example.reservation_de_salle.mapper;

import com.example.reservation_de_salle.dto.request.MeetingRoomDtoRequest;
import com.example.reservation_de_salle.dto.response.MeetingRoomDtoResponse;
import com.example.reservation_de_salle.dto.response.MeetingRoomReservationDtoResponse;
import com.example.reservation_de_salle.entities.MeetingRoom;
import com.example.reservation_de_salle.entities.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MeetingRoomMapper {
    MeetingRoomMapper INSTANCE = Mappers.getMapper(MeetingRoomMapper.class);
    MeetingRoom meetingRoomRequestDtoToMeetingRoom(MeetingRoomDtoRequest meetingRoomDtoRequest);

    MeetingRoomDtoResponse meetingRoomToMeetingRoomDtoResponse( MeetingRoom meetingRoom);
    MeetingRoomReservationDtoResponse reservationToMeetingRoomReservationDtoResponse(Reservation reservation);


}
