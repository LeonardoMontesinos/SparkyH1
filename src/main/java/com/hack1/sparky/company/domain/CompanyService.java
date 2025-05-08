package com.hack1.sparky.company.domain;

import com.hack1.sparky.company.infrastructure.CompanyRepository;
import com.hack1.sparky.user.domain.Rol;
import com.hack1.sparky.user.domain.User;
import com.hack1.sparky.user.infrastructure.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    @Transactional
    public Company createCompany(String name, User admin) {
        if (companyRepository.findByName(name).isPresent()) {
            throw new IllegalArgumentException("Company name already exists.");
        }
        admin.setRole(Rol.ROLE_COMPANY_ADMIN);
        Company company = new Company();
        company.setName(name);
        company.setActive(true);
        company.setAdmin(admin);
        userRepository.save(admin);
        return companyRepository.save(company);
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
    }

    @Transactional
    public Company updateCompany(Long id, String newName) {
        Company company = getById(id);
        company.setName(newName);
        return companyRepository.save(company);
    }

    @Transactional
    public void toggleStatus(Long id) {
        Company company = getById(id);
        company.setActive(!company.isActive());
        companyRepository.save(company);
    }
}

