package com.deepakameta.reviewms.service;

import com.deepakameta.reviewms.client.CompanyClient;
import com.deepakameta.reviewms.model.Review;
import com.deepakameta.reviewms.repository.ReviewRepository;
import com.deepakameta.reviewms.utils.ReviewException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyClient companyClient;


    @Override
    public List<Review> getReview(long companyId) throws ReviewException {
        boolean doesCompanyExist = companyClient.getCompany(companyId) != null;
        if (doesCompanyExist) {
            return reviewRepository.findByCompanyId(companyId);
        } else {
            throw new ReviewException("Company Not Found");
        }
    }

    @Override
    public String addReview(long companyId, Review review) throws ReviewException {
        boolean doesCompanyExist = companyClient.getCompany(companyId) != null;
        if (doesCompanyExist) {
            return reviewRepository.save(review).toString();
        } else {
            throw new ReviewException("Company Not Found");
        }
    }

    @Override
    public String updateReviewById(long companyId, long reviewId, Review review) throws ReviewException {
        boolean doesCompanyExist = companyClient.getCompany(companyId) != null;
        if (doesCompanyExist) {
            boolean isReviewExists = reviewRepository.existsById(reviewId);
            if (isReviewExists) {
                review.setReviewId(reviewId);
                reviewRepository.save(review);
                return "Review Updated Successfully";
            } else {
                throw new ReviewException("Review Not Found");
            }
        } else {
            throw new ReviewException("Company Not Found");
        }
    }

    @Override
    public String deleteReviewById(long companyId, long reviewId) throws ReviewException {
        boolean doesCompanyExist = companyClient.getCompany(companyId) != null;
        if (doesCompanyExist) {
            boolean isReviewExists = reviewRepository.existsById(reviewId);
            if (isReviewExists) {
                reviewRepository.deleteById(reviewId);
                return "Review Deleted Successfully";
            } else {
                throw new ReviewException("Review Not Found");
            }
        } else {
            throw new ReviewException("Company Not Found");
        }
    }
}