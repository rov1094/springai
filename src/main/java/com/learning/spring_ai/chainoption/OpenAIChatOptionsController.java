package com.learning.spring_ai.chainoption;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/openai/api")
public class OpenAIChatOptionsController {

    private final OpenAIChatServiceForChatOptions openAIChatService;

    //Field Injection Type by Contructor Injection
    public OpenAIChatOptionsController(OpenAIChatServiceForChatOptions openAIChatService) {
        this.openAIChatService = openAIChatService;
    }

    @GetMapping("/chatOptions")
    public String chat(@RequestParam String message) {
        return openAIChatService.askAI(message);
    }

    @GetMapping("/chatOptions/stream")
    public Flux<String> chatStream(@RequestParam String message) {
        return openAIChatService.askAIToStream(message);
    }

    @GetMapping(value="/chatOptions/streamForUI", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatStreamForUi (@RequestParam String message) {
        return openAIChatService.askAIToStream(message);
    }

}

