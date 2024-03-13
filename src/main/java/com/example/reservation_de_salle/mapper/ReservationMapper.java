package com.example.reservation_de_salle.mapper;

import com.example.reservation_de_salle.dto.request.ReservationDtoRequest;
import com.example.reservation_de_salle.dto.response.ReservationDtoResponse;
import com.example.reservation_de_salle.entities.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);
    Reservation reservationDtoRequestToReservation(ReservationDtoRequest reservationDtoRequest);

    @Mappings({
            @Mapping(target="meetingRoomId_target",source="reservation.room.id")
    })
    ReservationDtoResponse reservationToReservationDtoResponse(Reservation reservation);


}
