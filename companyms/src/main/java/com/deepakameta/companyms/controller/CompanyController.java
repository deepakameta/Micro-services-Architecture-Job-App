package com.deepakameta.companyms.controller;

import com.deepakameta.companyms.client.ReviewClient;
import com.deepakameta.companyms.model.Company;
import com.deepakameta.companyms.service.CompanyService;
import com.deepakameta.companyms.utils.CompanyException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("companies")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> geCompanyById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(companyService.getCompany(id));
        } catch (CompanyException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> saveCompany(@RequestBody Company company) {
        return ResponseEntity.ok(companyService.addCompany(company));
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<String> updateCompanyById(@PathVariable("companyId") long companyId, @RequestBody Company company) {
        try {
            String result = companyService.updateCompanyById(companyId, company);
            return ResponseEntity.ok(result);
        } catch (CompanyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable("companyId") long companyId) {
        try {
            String result = companyService.deleteCompanyById(companyId);
            return ResponseEntity.ok(result);
        } catch (CompanyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}