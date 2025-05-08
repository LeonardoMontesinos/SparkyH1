package com.hack1.sparky.IAModels;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.hack1.sparky.IAModels.dtos.ChatRequestDTO;
import com.hack1.sparky.IAModels.dtos.ChatResponseDTO;
import com.hack1.sparky.requestLog.domain.RequestLogService;
import com.hack1.sparky.requestLog.dto.CreateRequestLogDTO;
import com.hack1.sparky.restriction.domain.ModeloIA;
import com.hack1.sparky.user.domain.User;
import com.hack1.sparky.user.infrastructure.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AIService {

    private final UserRepository userRepository;
    private final RequestLogService logService;

    @Value("${github.token}")
    private String githubToken;

    private static final String ENDPOINT = "https://models.github.ai/inference";

    public ChatResponseDTO chat(Long userId, ChatRequestDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        ModeloIA modeloIA = user.getCompany().getDefaultModel();

        ChatCompletionsClient client = new ChatCompletionsClientBuilder()
                .credential(new AzureKeyCredential(githubToken))
                .endpoint(ENDPOINT)
                .buildClient();

        List<ChatRequestMessage> messages = new ArrayList<>();
        messages.add(new ChatRequestSystemMessage(dto.getSystemPrompt()));
        dto.getUserMessages().forEach(msg -> messages.add(new ChatRequestUserMessage(msg)));

        ChatCompletionsOptions options = new ChatCompletionsOptions(messages)
                .setModel(modeloIA.getPath());

        ChatCompletions result = client.complete(options);

        String content = result.getChoices().get(0).getMessage().getContent();
        int tokensUsed = result.getUsage() != null ? result.getUsage().getTotalTokens() : 0;

        CreateRequestLogDTO logDto = CreateRequestLogDTO.builder()
                .model(modeloIA)
                .prompt(String.join("\n", dto.getUserMessages()))
                .response(content)
                .tokensUsed(tokensUsed)
                .endpoint("chat")
                .build();

        logService.logRequest(userId, logDto);

        return ChatResponseDTO.builder()
                .content(content)
                .model(modeloIA.name())
                .build();
    }
}
