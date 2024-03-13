package com.example.reservation_de_salle.services.implementation;

import com.example.reservation_de_salle.Exception.AppException;
import com.example.reservation_de_salle.Exception.RessourceNotFoundException;
import com.example.reservation_de_salle.dto.request.ReservationDtoRequest;
import com.example.reservation_de_salle.dto.response.ReservationDtoResponse;
import com.example.reservation_de_salle.entities.MeetingRoom;
import com.example.reservation_de_salle.entities.Reservation;
import com.example.reservation_de_salle.mapper.ReservationMapper;
import com.example.reservation_de_salle.repository.MeetingRoomRepository;
import com.example.reservation_de_salle.repository.ReservationRepository;
import com.example.reservation_de_salle.services.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceReservationImpl implements ReservationService {
    private  final MeetingRoomRepository meetingRoomRepository;
    private  final ReservationRepository reservationRepository;
    @Override
    public List<ReservationDtoResponse> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(ReservationMapper.INSTANCE::reservationToReservationDtoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDtoResponse createReservation(ReservationDtoRequest reservationDto) {
        // Check if reservation already exist
        if(reservationRepository.existsById(reservationDto.id())){
            throw new AppException(HttpStatus.BAD_REQUEST,"Reservation already exist");
        }
        // Fetch the Writer entity using the provided writerId
        MeetingRoom meetingRoom = meetingRoomRepository.findById(reservationDto.meetingRoomId())
                .orElseThrow(() -> new RuntimeException("Reservataion not found"));

        // Map DTO to entity
        Reservation reservation = ReservationMapper.INSTANCE.reservationDtoRequestToReservation(reservationDto);

        // Associate the Room  with the Reservation
        reservation.setRoom(meetingRoom);

        // Save the Reservation entity in the database
        Reservation savedReservation = reservationRepository.save(reservation);

        // Map the saved Book entity to DTO and return
        return ReservationMapper.INSTANCE.reservationToReservationDtoResponse(savedReservation);
    }

    @Override
    public ReservationDtoResponse createReservationBetweenTwoDate(ReservationDtoRequest reservationDto, LocalDateTime startDate, LocalDateTime endDate) {
        // Fetch the MeetingRoom entity using the provided meetingRoomId
        MeetingRoom meetingRoom = meetingRoomRepository.findById(reservationDto.meetingRoomId())
                .orElseThrow(() -> new RuntimeException("Meeting room not found"));

        // Check if the meeting room is available between the provided start and end dates
        if (!isMeetingRoomAvailable(meetingRoom, startDate, endDate)) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Meeting room is not available for the specified period");
        }

        // Map DTO to entity
        Reservation reservation = ReservationMapper.INSTANCE.reservationDtoRequestToReservation(reservationDto);

        // Associate the meeting room with the reservation
        reservation.setRoom(meetingRoom);

        // Save the reservation entity in the database
        Reservation savedReservation = reservationRepository.save(reservation);

        // Map the saved reservation entity to DTO and return
        return ReservationMapper.INSTANCE.reservationToReservationDtoResponse(savedReservation);
    }

    private boolean isMeetingRoomAvailable(MeetingRoom meetingRoom, LocalDateTime startDate, LocalDateTime endDate) {
        // Retrieve reservations for the meeting room within the specified period
        List<Reservation> reservations = meetingRoom.getReservations();

        // Check if the meeting room is available for the specified period
        for (Reservation reservation : reservations) {
            LocalDateTime reservationStartTime = reservation.getStartDate();
            LocalDateTime reservationEndTime = reservation.getEndDate();

            // Check for overlapping reservations
            if (startDate.isBefore(reservationEndTime) && endDate.isAfter(reservationStartTime)) {
                return false; // Meeting room is not available
            }
        }

        return true;
    }

    @Override
    public ReservationDtoResponse updateReservation(Long reservationId, ReservationDtoRequest reservationDto) {
        //Get Reservation from Database
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(
                ()->new RessourceNotFoundException("Reservation","id",reservationId.toString()));
        if(reservationDto.id()!=null){
            reservation.setStartDate(reservationDto.startDate());
            reservation.setEndDate(reservationDto.endDate());
        };
        //Save update in the database
        Reservation updateReservation = reservationRepository.save(reservation);

        return ReservationMapper.INSTANCE.reservationToReservationDtoResponse(updateReservation);
    }

    @Override
    public void deleteReservation(Long reservationId) {
        //Get Reservation from Database
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(
                ()->new RessourceNotFoundException("Reservation","id",reservationId.toString()));

        reservationRepository.delete(reservation);
    }
}
