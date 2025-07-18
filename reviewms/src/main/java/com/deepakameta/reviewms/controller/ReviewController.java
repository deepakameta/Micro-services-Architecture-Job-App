package com.deepakameta.reviewms.controller;

import com.deepakameta.reviewms.model.Review;
import com.deepakameta.reviewms.service.ReviewService;
import com.deepakameta.reviewms.utils.ReviewException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("{companyId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long companyId) {
        try {
            return ResponseEntity.ok(reviewService.getReview(companyId));
        } catch (ReviewException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> saveReview(@PathVariable Long companyId, @RequestBody Review review) {
        try {
            return ResponseEntity.ok(reviewService.addReview(companyId, review));
        } catch (ReviewException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId,
                                               @RequestBody Review review) {
        try {
            return ResponseEntity.ok(reviewService.updateReviewById(companyId, reviewId, review));
        } catch (ReviewException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        try {
            return ResponseEntity.ok(reviewService.deleteReviewById(companyId, reviewId));
        } catch (ReviewException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
