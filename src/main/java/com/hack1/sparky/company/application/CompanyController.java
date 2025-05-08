package com.hack1.sparky.company.application;

import com.hack1.sparky.company.domain.Company;
import com.hack1.sparky.company.domain.CompanyService;
import com.hack1.sparky.company.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/companies")
@PreAuthorize("hasRole('ROLE_SPARKY_ADMIN')")
@RequiredArgsConstructor
public class CompanyController {

    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDTO) {
        return ResponseEntity.ok(companyService.createCompany(companyDTO));
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) {
        return ResponseEntity.ok(companyService.updateCompany(id, companyDTO));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<CompanyDTO> toggleCompanyStatus(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.toggleCompanyStatus(id));
    }

    @GetMapping("/{id}/consumption")
    public ResponseEntity<ConsumptionReportDTO> getCompanyConsumptionReport(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getConsumptionReport(id));
    }
}
