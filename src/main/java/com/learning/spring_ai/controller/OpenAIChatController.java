//package com.learning.spring_ai.controller;
//
//import com.learning.spring_ai.service.OpenAIChatService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/openai/api")
//public class OpenAIChatController {
//
//    private final OpenAIChatService openAIChatService;
//
//    //Field Injection Type by Contructor Injection
//    public OpenAIChatController(OpenAIChatService openAIChatService) {
//        this.openAIChatService = openAIChatService;
//    }
//
//    @GetMapping("/chat")
//    public String chat(@RequestParam String message) {
//        //return openAIChatService.chatWithOpenAILLM(message);
//        return null;
//    }
//
//}
