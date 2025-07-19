package com.deepakameta.companyms.client;

import com.deepakameta.companyms.model.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "reviewms")
public interface ReviewClient {

    @GetMapping("/reviews/{companyId}/")
    List<Review> getReviews(@PathVariable long companyId);
}
