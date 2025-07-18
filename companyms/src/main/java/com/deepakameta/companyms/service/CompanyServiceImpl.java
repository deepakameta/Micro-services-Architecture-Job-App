package com.deepakameta.companyms.service;

import com.deepakameta.companyms.client.ReviewClient;
import com.deepakameta.companyms.model.Company;
import com.deepakameta.companyms.repository.CompanyRepository;
import com.deepakameta.companyms.utils.CompanyException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ReviewClient reviewClient;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll().stream().map(company -> {
            try {
                company.setReviews(reviewClient.getReviews(company.getCompanyId()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return company;
        }).toList();
    }

    @Override
    public Company getCompany(long id) throws CompanyException {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            return company.get();
        } else {
            throw new CompanyException("Company with id: " + id + "does not exist.");
        }
    }

    @Override
    public String addCompany(Company company) {
        return companyRepository.save(company).getName();
    }

    @Override
    public String updateCompanyById(long companyId, Company company) throws CompanyException {
        boolean isExist = companyRepository.existsById(companyId);
        if (isExist) {
            company.setCompanyId(companyId);
            return companyRepository.save(company).toString();
        } else {
            throw new CompanyException("Company with id: " + companyId + "does not exist.");
        }
    }

    @Override
    public String deleteCompanyById(long companyId) throws CompanyException {
        boolean isExist = companyRepository.existsById(companyId);
        if (isExist) {
            companyRepository.deleteById(companyId);
            return "Company deleted successfully";
        } else {
            throw new CompanyException("Company with id: " + companyId + "does not exist.");
        }
    }
}
