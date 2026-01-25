package com.learning.spring_ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class MultipleChatService {

    private final ChatClient googleGenAiChatClient;
    private final ChatClient openAiChatClient;
    private final ChatClient ollamaChatClient;

    public MultipleChatService(ChatClient googleGenAiChatClient,ChatClient openAiChatClient, ChatClient ollamaChatClient) {
        this.googleGenAiChatClient = googleGenAiChatClient;
        this.openAiChatClient = openAiChatClient;
        this.ollamaChatClient = ollamaChatClient;
    }

    public String chatWithOpenAI(String message) {
        return openAiChatClient
                .prompt(message)
                .call()
                .content();
    }

    public String chatWithGemini(String message) {
        return googleGenAiChatClient
                .prompt(message)
                .call()
                .content();
    }

    public String ollamaChatModel(String message) {
        return ollamaChatClient
                .prompt(message)
                .call()
                .content();
    }
}
