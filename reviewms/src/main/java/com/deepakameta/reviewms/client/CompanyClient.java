package com.deepakameta.reviewms.client;

import com.deepakameta.reviewms.model.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "companyms")
public interface CompanyClient {

    @GetMapping("/companies/{id}")
    Company getCompany(@PathVariable long id);
}
