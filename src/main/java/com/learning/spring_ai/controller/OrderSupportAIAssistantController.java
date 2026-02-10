package com.learning.spring_ai.controller;

import com.learning.spring_ai.service.promptstuffing.OrderPromptStuffing;
import com.learning.spring_ai.service.prompttemplate.OrderSupportAIAssistantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderSupportAIAssistantController {

    private OrderSupportAIAssistantService orderSupportAIAssistantService;
    private OrderPromptStuffing orderPromptStuffing;

    public OrderSupportAIAssistantController(OrderSupportAIAssistantService orderSupportAIAssistantService,
                                             OrderPromptStuffing orderPromptStuffing) {
        this.orderSupportAIAssistantService = orderSupportAIAssistantService;
        this.orderPromptStuffing = orderPromptStuffing;
    }

    @GetMapping("/order-support")
    public String getOrderSupportAIAssistant(@RequestParam String customerName,
                                             @RequestParam String orderId,
                                             @RequestParam String customerMessage){
        return this.orderSupportAIAssistantService
                .assistWithOrderSupport(customerName, orderId, customerMessage);
    }

    @GetMapping("/order-ai-assistant")
    public String talkToAIAssistant(@RequestParam String customerName, @RequestParam String orderId, @RequestParam String customerMessage){
        return this.orderPromptStuffing.talkToAIAssistant(customerName, orderId, customerMessage);
    }
}
