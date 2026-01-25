package com.learning.spring_ai.controller;

import com.learning.spring_ai.service.MultipleChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multi-model/chat/api")
public class MultiChatController {

    private final MultipleChatService multipleChatService;

    public MultiChatController(MultipleChatService multipleChatService) {
        this.multipleChatService = multipleChatService;
    }

    @GetMapping("/gemini")
    public String googleGenAiChatModel(@RequestParam String message) {
        return multipleChatService.chatWithGemini(message);
    }

    @GetMapping("/openAI")
    public String openAiChatModel(@RequestParam String message) {
        return multipleChatService.chatWithOpenAI(message);
    }

    @GetMapping("/ollama")
    public String ollamaChatModel(@RequestParam String message) {
        return multipleChatService.ollamaChatModel(message);
    }
}
