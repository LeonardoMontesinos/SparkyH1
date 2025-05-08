package com.hack1.sparky.requestLog.domain;

import com.hack1.sparky.requestLog.dto.CreateRequestLogDTO;
import com.hack1.sparky.requestLog.infrastructure.RequestLogRepository;
import com.hack1.sparky.restriction.domain.ModeloIA;
import com.hack1.sparky.user.domain.User;
import com.hack1.sparky.user.infrastructure.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestLogService {

    private final RequestLogRepository requestLogRepository;
    private final UserRepository userRepository;

    public void logRequest(Long userId, CreateRequestLogDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        RequestLog log = RequestLog.builder()
                .user(user)
                .timestamp(LocalDateTime.now())
                .model(dto.getModel())
                .prompt(dto.getPrompt())
                .response(dto.getResponse())
                .tokensUsed(dto.getTokensUsed())
                .endpoint(dto.getEndpoint())
                .build();

        requestLogRepository.save(log);
    }

    public List<RequestLog> getUserLogs(Long userId) {
        return requestLogRepository.findByUserId(userId);
    }

    public List<RequestLog> getUserLogsByModel(Long userId,ModeloIA model) {
        return requestLogRepository.findByUserIdAndModel(userId, model);
    }

    public int getTotalTokensUsed(Long userId, ModeloIA model) {
        return requestLogRepository.totalTokensUsedByUserAndModel(userId, model).orElse(0);
    }
}

