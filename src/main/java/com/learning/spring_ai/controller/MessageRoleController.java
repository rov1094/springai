package com.learning.spring_ai.controller;

import com.learning.spring_ai.messageroles.OpenAIMessageRoleService;
import com.learning.spring_ai.service.OpenAIChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MessageRoleController {

    private final OpenAIMessageRoleService openAIMessageRoleService;

    public MessageRoleController(OpenAIMessageRoleService openAIMessageRoleService) {
        this.openAIMessageRoleService = openAIMessageRoleService;
    }

    @GetMapping("/v1/customer/checkPolicy")
    public String customerQuery(String message){
        return openAIMessageRoleService.customerPromptInjection(message);
    }

    @GetMapping("/v2/customer/checkPolicy")
    public String customerQueryV2(String message){
        return openAIMessageRoleService.checkPolicy(message);
    }

    @GetMapping("/v3/customer/checkPolicy")
    public String customerQueryV3(String message){
        return openAIMessageRoleService.checkPolicyInternal(message);
    }

}
