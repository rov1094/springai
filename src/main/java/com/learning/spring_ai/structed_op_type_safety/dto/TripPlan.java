package com.learning.spring_ai.structed_op_type_safety.dto;

import java.util.List;

public record TripPlan(
        String destination,
        Integer totalDays,
        List<Plan> plans
) {
}
