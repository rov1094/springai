package com.learning.spring_ai.spring_ai_advisor;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * We will understand , prompt template here
 */
@Service
public class OrderSupportAIAdvisor {

    @Value("classpath:prompts/order_system_template.st")
    private Resource orderSystemTemplate;

    @Value("classpath:prompts/order_user_template.st")
    private Resource orderUserTemplate;

    private ChatClient chatClient;

    public OrderSupportAIAdvisor(ChatClient openAiChatClient) {
        this.chatClient = openAiChatClient;
    }

    public String assistWithOrderSupport(String customerName, String orderId, String customerMessage){
        return chatClient
                .prompt()
                .advisors(List.of(new SimpleLoggerAdvisor()))
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
