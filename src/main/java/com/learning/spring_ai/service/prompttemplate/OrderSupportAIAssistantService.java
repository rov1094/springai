package com.learning.spring_ai.service.prompttemplate;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 * We will understand , prompt template here
 */
@Service
public class OrderSupportAIAssistantService {

    @Value("classpath:prompts/order_system_template.st")
    private Resource orderSystemTemplate;

    @Value("classpath:prompts/order_user_template.st")
    private Resource orderUserTemplate;

    private ChatClient chatClient;

    public OrderSupportAIAssistantService(ChatClient openAiChatClient) {
        this.chatClient = openAiChatClient;
    }

    public String assistWithOrderSupport(String customerName, String orderId, String customerMessage){
        return chatClient
                .prompt()
                .system(orderSystemTemplate)
                .user(promptUserSpec -> promptUserSpec.text(orderUserTemplate)
                        .param("customerName",customerName)
                        .param("orderId",orderId)
                        .param("customerMessage",customerMessage)
                )
                .call()
                .content();
    }
}
