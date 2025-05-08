package com.hack1.sparky.restriction.infrastructure;

import com.hack1.sparky.restriction.domain.ModeloIA;
import com.hack1.sparky.restriction.domain.Restriction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestrictionRepository extends JpaRepository<Restriction, Long> {

    List<Restriction> findByCompanyId(Long companyId);

    Optional<Restriction> findByCompanyIdAndModel(Long companyId, ModeloIA model);
}
