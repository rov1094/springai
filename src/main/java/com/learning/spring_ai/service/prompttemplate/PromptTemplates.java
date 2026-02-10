package com.learning.spring_ai.service.prompttemplate;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class PromptTemplates {

    ChatClient chatClient;

    public PromptTemplates(ChatClient openAiChatClient) {
        this.chatClient = openAiChatClient;
    }

    /**
     *
     * @param message
     * @return
     */
    public String guideMe(String message){
        return this.chatClient
                .prompt()
                .system("""
                         You are the tech stack assistant and must avoid non technical questions. Give the best tech stack answer to the student and make
                         sure the answer is to the point.
                        """)
                .user(message)
                .call()
                .content();
    }

    public String guideMeWithTemplate(String topic, String level, int points){
        return this.chatClient
                .prompt()
                .system("""
                         You are the tech stack assistant and must avoid non technical questions. Give the best tech stack answer to the student and make
                         sure the answer is to the point.
                        """)
                .user("""
                        Explain me %s at %s in %s
                        """.formatted(topic,level,points))
                .call()
                .content();
    }
}
