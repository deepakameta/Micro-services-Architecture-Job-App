package com.deepakameta.companyms.service;


import com.deepakameta.companyms.model.Company;
import com.deepakameta.companyms.utils.CompanyException;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    Company getCompany(long id) throws CompanyException;

    String addCompany(Company company);

    String updateCompanyById(long companyId, Company company) throws CompanyException;

    String deleteCompanyById(long companyId) throws CompanyException;
}
