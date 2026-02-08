package com.learning.spring_ai.messageroles;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class OpenAIMessageRoleService {

    // S1: Regulatory & Compliance
    public static final String SEBI_COMPLIANCE = """
        You are a SEBI-compliant stock market advisory assistant.
        Do NOT give direct buy, sell, or hold recommendations.
        Do NOT provide price targets or timing advice.
        Always include a clear risk disclaimer.
        """;

    // S2: Equity Research Expertise
    public static final String EQUITY_RESEARCH_ANALYST = """
        You are a senior equity research analyst.
        Explain answers using business fundamentals.
        Use simple language and bullet points.
        Focus on long-term investing perspective.
        """;
    //User message
    public static final String CUSTOMER_MESSAGE = """
        Policy details:
        - Policy premium range: minimum 5,000 and maximum 10,000
        - Maximum claim amount: 150,000
        
        Customer says:
        "%s"
        """;


    private final ChatClient openAIChatClient;

    public OpenAIMessageRoleService(ChatClient openAiChatClient) {
        this.openAIChatClient = openAiChatClient;
//      this.openAIChatClient = chatClientBuilder.defaultSystem().build(); // we can use default System as well, and will be overridden if defined below
    }

    /**
     * To demo Prompt Injection
     * @param message
     * @return
     */
    public String customerPromptInjection(String message){
        UserMessage userMessage=new UserMessage(CUSTOMER_MESSAGE.formatted(message));
        Prompt prompt=new Prompt(userMessage);
        return openAIChatClient
                .prompt(prompt)
                .call()
                .content();
    }

    /**
     * To restrict the prompt injection for Customer
     * @param message
     * @return
     */
    public String checkPolicy(String message){
        SystemMessage systemMessage=new SystemMessage(SEBI_COMPLIANCE);
        UserMessage userMessage=new UserMessage(CUSTOMER_MESSAGE.formatted(message));
        Prompt prompt=new Prompt(systemMessage,userMessage);
        return openAIChatClient
                .prompt(prompt)
                .call()
                .content();
    }

    /**
     * To restrict the prompt injection for internal
     * @param message
     * @return
     */
    public String checkPolicyInternal(String message){
    return openAIChatClient
            .prompt()
            .user(CUSTOMER_MESSAGE.formatted(message))
            .system(EQUITY_RESEARCH_ANALYST)
            .call().content();
    }

}
