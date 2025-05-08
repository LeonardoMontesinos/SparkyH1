package com.hack1.sparky.limit.infrastructure;

import com.hack1.sparky.limit.domain.Limit;
import com.hack1.sparky.restriction.domain.ModeloIA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LimitRepository extends JpaRepository<Limit, Long> {

    List<Limit> findByUserId(Long userId);

    Optional<Limit> findByUserIdAndModel(Long userId, ModeloIA model);
}
