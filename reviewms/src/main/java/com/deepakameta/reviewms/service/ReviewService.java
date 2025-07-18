package com.deepakameta.reviewms.service;


import com.deepakameta.reviewms.model.Review;
import com.deepakameta.reviewms.utils.ReviewException;

import java.util.List;

public interface ReviewService {

    List<Review> getReview(long companyId) throws ReviewException;

    String addReview(long companyId, Review review) throws ReviewException;

    String updateReviewById(long companyId, long reviewId, Review review) throws ReviewException;

    String deleteReviewById(long companyId, long reviewId) throws ReviewException;
}
