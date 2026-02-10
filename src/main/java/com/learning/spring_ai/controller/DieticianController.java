package com.learning.spring_ai.controller;

import com.learning.spring_ai.service.promptstuffing.DieticianPromptStuffing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DieticianController {

    private DieticianPromptStuffing dieticianPromptStuffing;

    public DieticianController(DieticianPromptStuffing dieticianPromptStuffing) {
        this.dieticianPromptStuffing = dieticianPromptStuffing;
    }

    @GetMapping("/dietAIAssistant")
    public String talkWithDietAIAssistant(@RequestParam String userName,
                                          @RequestParam int currentWeight,
                                          @RequestParam int targetWeight,
                                          @RequestParam String userMessage,
                                          @RequestParam String height,
                                          @RequestParam String foodType){
        return dieticianPromptStuffing.getDietPlan(userName,
                userMessage,
                currentWeight,
                targetWeight,
                height,
                foodType);
    }
}
