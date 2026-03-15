package com.learning.spring_ai.controller;

import com.learning.spring_ai.promptstuffing.OrderPromptStuffing;
import com.learning.spring_ai.spring_ai_advisor.OrderSupportAIAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderSupportAIAdvisorController {

    private OrderSupportAIAdvisor orderSupportAIAdvisor;

    public OrderSupportAIAdvisorController(OrderSupportAIAdvisor orderSupportAIAssistantService) {
        this.orderSupportAIAdvisor = orderSupportAIAssistantService;
    }

    @GetMapping("/order-support-advisor")
    public String getOrderSupportAIAssistant(@RequestParam String customerName,
                                             @RequestParam String orderId,
                                             @RequestParam String customerMessage){
        return this.orderSupportAIAdvisor
                .assistWithOrderSupport(customerName, orderId, customerMessage);
    }

}
