package com.example.gestion_livres.services.interfaces;

import com.example.gestion_livres.entities.Borrow;
import com.example.gestion_livres.entities.Review;

import java.util.List;

public interface ReviewService {
    Review addReview(Review review);
    List<Review> getAllReviews();
    List<Review> getReviewsByBookId(Long bookId);
}
