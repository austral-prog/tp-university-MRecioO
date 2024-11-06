package com.university.approval_criteria.types;

import com.university.approval_criteria.AbstractCriteria;
import com.university.evaluation.Evaluation;


import java.util.List;

public class CriteriaFactory {

    public static AbstractCriteria createCriteria(String criteriaType, List<Evaluation> evaluations, Float grade) {
        switch (criteriaType) {
            case "AVERAGE_ABOVE_VALUE":
                return new AverageAboveValue(criteriaType, evaluations, grade);
            case "MAX_ABOVE_VALUE":
                return new MaxAboveValue(criteriaType, evaluations, grade);
            case "MIN_ABOVE_VALUE":
                return new MinAboveValue(criteriaType, evaluations, grade);
            default:
                throw new IllegalArgumentException("Tipo de criterio de aprobacion no reconocido: " + criteriaType);
        }
    }
}
