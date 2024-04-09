package com.example.gestion_livres.Exception;


import java.util.Date;

public record ErrorDetail(
        Date timestamp,
        int status,
        String error,
        String message,
        String details
) {
}