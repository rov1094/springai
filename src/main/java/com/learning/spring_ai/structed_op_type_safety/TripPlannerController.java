package com.learning.spring_ai.structed_op_type_safety;

import com.learning.spring_ai.structed_op_type_safety.dto.TripPlan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trips")
public class TripPlannerController {

    private final TravelPlannerService travelPlannerService;

    public TripPlannerController(TravelPlannerService travelPlannerService) {
        this.travelPlannerService = travelPlannerService;
    }

    @GetMapping("/plan-trip")
    public String getTripPlans(@RequestParam String message){
        return travelPlannerService.getTripPlans(message);
    }

    @GetMapping("/plan-trip-structured")
    public TripPlan getTripPlansStructured(@RequestParam String message){
        return travelPlannerService.getTripPlansStructured(message);
    }

    @GetMapping("/plan-trip-spot")
    public List<String> getTripSpot(@RequestParam String message){
        return travelPlannerService.getTripSpot(message);
    }

    @GetMapping("/plan-trip-map")
    public Map<String, Object> getTripPlansMap(@RequestParam String message){
        return travelPlannerService.getTripPlansMap(message);
    }

    @GetMapping("/completeTripPlan")
    public List<TripPlan> getCompletePlan(@RequestParam String message){
        return travelPlannerService.getCompleteTripPlan(message);
    }
}
