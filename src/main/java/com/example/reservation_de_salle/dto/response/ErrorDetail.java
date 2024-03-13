package com.example.reservation_de_salle.dto.response;


import java.util.Date;

public record ErrorDetail(
        Date timestamp,
        int status,
        String error,
        String message,
        String details
) {
}