package com.learning.spring_ai.service.promptstuffing;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 * We will understand , prompt template here
 */
@Service
public class OrderPromptStuffing {

    @Value("classpath:prompts/order_system_policy.st")
    private Resource orderSystemPolicy;
    @Value("classpath:prompts/order_user_template.st")
    private Resource orderUserTemplate;

    private ChatClient chatClient;

    public OrderPromptStuffing(ChatClient openAiChatClient) {
        this.chatClient = openAiChatClient;
    }

    public String talkToAIAssistant(String customerName, String orderId, String customerMessage){
        return chatClient
                .prompt()
                .system(orderSystemPolicy)
                .user(promptUserSpec -> promptUserSpec.text(orderUserTemplate)
                        .param("customerName",customerName)
                        .param("orderId",orderId)
                        .param("customerMessage",customerMessage)
                )
                .call()
                .content();
    }
}
