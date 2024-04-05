package com.example.gestion_livres.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String summary;
    private String genre;
    private LocalDateTime borrowingDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}