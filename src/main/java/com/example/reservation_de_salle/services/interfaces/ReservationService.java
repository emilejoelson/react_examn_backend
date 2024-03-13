package com.example.reservation_de_salle.services.interfaces;

import com.example.reservation_de_salle.dto.request.ReservationDtoRequest;
import com.example.reservation_de_salle.dto.response.ReservationDtoResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    List<ReservationDtoResponse> getAllReservations();
    ReservationDtoResponse createReservation( ReservationDtoRequest reservationDto);
    ReservationDtoResponse createReservationBetweenTwoDate(ReservationDtoRequest reservationDto, LocalDateTime startDate, LocalDateTime endDate);
    ReservationDtoResponse updateReservation(Long reservationId, ReservationDtoRequest reservationDto);
    void deleteReservation(Long reservationId);
}
