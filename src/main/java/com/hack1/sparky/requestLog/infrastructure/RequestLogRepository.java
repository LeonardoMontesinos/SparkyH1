package com.hack1.sparky.requestLog.infrastructure;

import com.hack1.sparky.requestLog.domain.RequestLog;
import com.hack1.sparky.restriction.domain.ModeloIA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {

    List<RequestLog> findByUserId(Long userId);

    List<RequestLog> findByUserIdAndModel(Long userId, ModeloIA model);

    List<RequestLog> findByUserIdAndTimestampBetween(Long userId, LocalDateTime start, LocalDateTime end);

    @Query("SELECT SUM(r.tokensUsed) FROM RequestLog r WHERE r.user.id = :userId AND r.model = :model")
    Optional<Integer> totalTokensUsedByUserAndModel(@Param("userId") Long userId, @Param("model") ModeloIA model);
}
