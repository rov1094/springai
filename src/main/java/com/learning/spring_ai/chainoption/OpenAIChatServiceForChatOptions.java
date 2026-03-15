package com.learning.spring_ai.chainoption;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class OpenAIChatServiceForChatOptions {

    //ChatClient is provided by Spring boot and help to build correspnding chat model by taking help from autoconfiguration
    private final ChatClient chatClient;

    public OpenAIChatServiceForChatOptions(ChatClient openAiChatClient) {
        this.chatClient = openAiChatClient;
    }

    public String askAI(String message) {
        // these can be configured using yaml file as well
        ChatOptions chatOptions = ChatOptions.builder()
                .model("gpt-4o-mini")//Model to be used
                .temperature(0.3)//Depends for which we want to use, lower is consistent (config-0,coding-0.3) more is creativity (1.2 for scripts)
                .maxTokens(400)//depends on used  but safe value is 400-500 tokens
//                .frequencyPenalty(0.7) // this is to control the frequency of words in output, (more repetitive)low<freq<high(less repetitive) (0.7 or 0.8 is recommended)
//                .presencePenalty(0.7) // this is increase the thought or new words in response and reduce repetitive words.
//                .stopSequences(List.of("}"))//where to stop response generation from AI when the condition match. Example of json ending with }
//                //.topK(3)//top count of options , not to use and spring manage , top 3 items (GSP is not supporting it)
//                .topP(0.5)//percentage of options, top 50% items
                .build();

        return chatClient
                .prompt(message) //Prompt to LLM
                .options(chatOptions)
                .call() // Call the LLM
                .content(); //Return the response
    }

    public Flux<String> askAIToStream(String message) {

        return chatClient
                .prompt(message) //Prompt to LLM
                .stream() // Call the LLM
                .content(); //Return the response
    }





}

