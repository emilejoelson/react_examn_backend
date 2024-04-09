package com.example.gestion_livres.repository;

import com.example.gestion_livres.entities.Borrow;
import com.example.gestion_livres.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByBookId(Long bookId);
}
