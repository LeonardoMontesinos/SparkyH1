package com.hack1.sparky.restriction.application;

import com.hack1.sparky.restriction.domain.Restriction;
import com.hack1.sparky.restriction.domain.RestrictionService;
import com.hack1.sparky.restriction.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/restrictions")
@PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
@RequiredArgsConstructor

public class RestrictionController {

    private final RestrictionService restrictionService;

    @PostMapping
    public ResponseEntity<Void> createRestriction(@RequestBody @Valid CreateRestrictionDTO request) {
        Restriction restriction = request.toEntity();
        restrictionService.createRestriction(request.getCompanyId(), restriction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<RestrictionDto>> listRestrictions(@RequestParam Long companyId) {
        return ResponseEntity.ok(restrictionService.getAllByCompany(companyId).stream()
                .map(RestrictionDto::fromEntity).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRestriction(@PathVariable Long id, @RequestBody @Valid UpdateRestrictionRequest request) {
        restrictionService.updateRestriction(id, request.toEntity());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestriction(@PathVariable Long id) {
        restrictionService.deleteRestriction(id);
        return ResponseEntity.noContent().build();
    }
}
