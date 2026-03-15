package com.learning.spring_ai.structed_op_type_safety.dto;

import java.util.List;

public record Plan(
        String fromLocation,
        String toLocation,
        String transport,
        int dayNumber,
        List<String> activities
) {
}
