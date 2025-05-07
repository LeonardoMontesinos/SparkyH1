package com.hack1.sparky.IA.domain;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.hack1.sparky.Empresa.domain.Empresa;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ChatIAService {

    @Value("${github.token}")
    private String githubToken;

    public String procesarConsulta(Empresa empresa, String mensajeUsuario) {
        String modelo = empresa.getModeloIA().getNombreEnGithub();

        String endpoint = "https://models.github.ai/inference";
        ChatCompletionsClient client = new ChatCompletionsClientBuilder()
                .credential(new AzureKeyCredential(githubToken))
                .endpoint(endpoint)
                .buildClient();

        List<ChatRequestMessage> mensajes = Arrays.asList(
                new ChatRequestSystemMessage(""),
                new ChatRequestUserMessage(mensajeUsuario)
        );

        ChatCompletionsOptions options = new ChatCompletionsOptions(mensajes);
        options.setModel(modelo);

        ChatCompletions completions = client.complete(options);

        // Acceder a la primera respuesta de la lista de choices
        if (completions.getChoices() != null && !completions.getChoices().isEmpty()) {
            ChatChoice firstChoice = completions.getChoices().get(0);
            return firstChoice.getMessage().getContent();
        } else {
            return "No se recibió respuesta del modelo.";
        }
    }
}
