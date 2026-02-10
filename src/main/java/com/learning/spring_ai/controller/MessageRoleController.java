package com.learning.spring_ai.controller;

import com.learning.spring_ai.messageroles.OpenAIMessageRoleService;
import com.learning.spring_ai.service.prompttemplate.PromptTemplates;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MessageRoleController {

    private final OpenAIMessageRoleService openAIMessageRoleService;
    private final PromptTemplates promptTemplates;

    public MessageRoleController(OpenAIMessageRoleService openAIMessageRoleService, PromptTemplates promptTemplates) {
        this.openAIMessageRoleService = openAIMessageRoleService;
        this.promptTemplates=promptTemplates;
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

    /**
     * Prompt Explain me kafka for beginner level in 5 points
     * @param message
     * @return
     */
    @GetMapping("/guide")
    public String guideUser(@RequestParam String message){
        return promptTemplates.guideMe(message);
    }

    @GetMapping("/guideMe")
    public String guideMe(@RequestParam String topic,@RequestParam String level,@RequestParam int points){
        return promptTemplates.guideMeWithTemplate(topic,level,points);
    }

}
