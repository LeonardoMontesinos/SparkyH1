package com.hack1.sparky.restriction.domain;

import com.hack1.sparky.company.domain.Company;
import com.hack1.sparky.company.infrastructure.CompanyRepository;
import com.hack1.sparky.restriction.infrastructure.RestrictionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestrictionService {

    private final RestrictionRepository restrictionRepository;
    private final CompanyRepository companyRepository;

    public Restriction createRestriction(Long companyId, Restriction restriction) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        restriction.setCompany(company);
        return restrictionRepository.save(restriction);
    }

    public List<Restriction> getAllByCompany(Long companyId) {
        return restrictionRepository.findByCompanyId(companyId);
    }

    public Restriction updateRestriction(Long id, Restriction updated) {
        Restriction existing = restrictionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restriction not found"));
        existing.setMaxRequestsPerDay(updated.getMaxRequestsPerDay());
        existing.setMaxTokensPerRequest(updated.getMaxTokensPerRequest());
        return restrictionRepository.save(existing);
    }

    public void deleteRestriction(Long id) {
        restrictionRepository.deleteById(id);
    }
}

