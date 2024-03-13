package com.example.reservation_de_salle.controller;

import com.example.reservation_de_salle.dto.request.ReservationDtoRequest;
import com.example.reservation_de_salle.dto.response.ReservationDtoResponse;
import com.example.reservation_de_salle.services.interfaces.ReservationService;
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
@RequestMapping("/reservations")
@CrossOrigin("*")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDtoResponse> createReservation(@Valid @RequestBody ReservationDtoRequest reservationDto) {
        ReservationDtoResponse response = reservationService.createReservation(reservationDto);
        return ResponseEntity.created(URI.create("/reservations/" + response.id())).body(response);
    }

    @PostMapping("/between_two_date")
    public ResponseEntity<ReservationDtoResponse> createReservationBetweenTwoDate(
            @Valid @RequestBody ReservationDtoRequest reservationDto,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endDate) {
        ReservationDtoResponse response = reservationService.createReservationBetweenTwoDate(reservationDto,startDate,endDate);
        return ResponseEntity.created(URI.create("/reservations/" + response.id())).body(response);
    }
    @GetMapping
    public ResponseEntity<List<ReservationDtoResponse>> getAllReseravtions() {
        List<ReservationDtoResponse> response = reservationService.getAllReservations();
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ReservationDtoResponse> updateReservation(
            @Valid @PathVariable(name="id") Long id, @RequestBody ReservationDtoRequest reservationDto) {
        ReservationDtoResponse response = reservationService.updateReservation(id, reservationDto);
        return  ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable(name="id") Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok("Reservation deleted successfully");
    }
}
