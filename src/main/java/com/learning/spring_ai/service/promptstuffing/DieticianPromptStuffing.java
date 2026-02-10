package com.learning.spring_ai.service.promptstuffing;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class DieticianPromptStuffing {

    @Value("classpath:prompts/dietician/diet_system_template.st")
    private Resource dietSystemTemplate;
    @Value("classpath:prompts/dietician/diet_user_template.st")
    private Resource dietUserTemplate;

    private ChatClient chatClient;

    public DieticianPromptStuffing(ChatClient openAiChatClient) {
        this.chatClient = openAiChatClient;
    }

    public String getDietPlan(String userName,
                              String userMessage,
                              int currentWeight,
                              int targetWeight,
                              String height,
                              String foodType){
        return chatClient
                .prompt()
                .system(dietSystemTemplate)
                .user(promptUserSpec -> promptUserSpec.text(dietUserTemplate)
                        .param("userName", userName)
                        .param("currentWeight", currentWeight)
                        .param("targetWeight", targetWeight)
                        .param("foodType", foodType)
                        .param("height", height)
                        .param("userMessage", userMessage))
                .call()
                .content();

    }
}
