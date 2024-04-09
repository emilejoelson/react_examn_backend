package com.example.gestion_livres.controller;

import com.example.gestion_livres.entities.Borrow;
import com.example.gestion_livres.entities.Review;
import com.example.gestion_livres.services.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
@CrossOrigin("*")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> addReview(@Valid @RequestBody Review review){
        Review response = reviewService.addReview(review);
        return new ResponseEntity<>(response, HttpStatus.OK);
    };

    @GetMapping
   public ResponseEntity<Review> getAllReviews(){
        List<Review> allReviews = reviewService.getAllReviews();
        return new ResponseEntity(allReviews,HttpStatus.OK);
    };

    @GetMapping("/byBookId/{bookId}")
    public ResponseEntity<List<Review>> getReviewsByBookId(@PathVariable Long bookId) {
        List<Review> reviews = reviewService.getReviewsByBookId(bookId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
