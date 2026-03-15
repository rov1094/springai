package com.learning.spring_ai.structed_op_type_safety;

import com.learning.spring_ai.structed_op_type_safety.dto.TripPlan;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TravelPlannerService {

    @Value("classpath:prompts/tripplanner/tripplanner.st")
    private Resource tripPlanResource;

    private final ChatClient chatClient;

    public TravelPlannerService(ChatClient openAiChatClient) {
        this.chatClient = openAiChatClient;
    }

    public String getTripPlans(String message){
        return chatClient
                .prompt()
                .system(tripPlanResource)
                .user(message)
                .call()
                .content();
    }
    //using just the entity
    public TripPlan getTripPlansStructured(String message){
        return chatClient
                .prompt(message)
                .system(tripPlanResource)
                .call()
                .entity(TripPlan.class);
    }
    //Using BeanOutputConverter
    public TripPlan getTripPlansStructured2(String message){
        return chatClient
                .prompt(message)
                .system(tripPlanResource)
                .call()
                .entity(new BeanOutputConverter<>(TripPlan.class));
    }

    //Map<String,Object> as output
    public Map<String, Object> getTripPlansMap(String message){
        return chatClient
                .prompt(message)
//                .system(tripPlanResource)
                .call()
                .entity(new MapOutputConverter());
    }

    public List<String> getTripSpot(String message) {
        return chatClient
                .prompt()
                .user(message)
                .call()
                .entity(new ListOutputConverter());
    }


    public List<TripPlan> getCompleteTripPlan(String message) {
        return chatClient
                .prompt()
                .user(message)
                .call()
                .entity(new ParameterizedTypeReference<>() {});
    }


}
