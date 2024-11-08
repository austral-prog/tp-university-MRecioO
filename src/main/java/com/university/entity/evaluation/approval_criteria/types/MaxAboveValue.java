package com.university.entity.evaluation.approval_criteria.types;

import com.university.entity.evaluation.approval_criteria.AbstractCriteria;
import com.university.entity.evaluation.Evaluation;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class MaxAboveValue extends AbstractCriteria {

    public MaxAboveValue(String criteriaType, List<Evaluation> evaluationList, float grade) {
        super(criteriaType, evaluationList, grade);
    }
    @Override
    protected boolean checkIfIsApproved(){
        if(evaluationList.size() == 0){
            return false;
        }

        List<Float> allEvaluationsGrades = new ArrayList<>();
        for (Evaluation evaluation : evaluationList) {
            allEvaluationsGrades.add((float) evaluation.calculateGrade());
        }
        float maxEvaluationGrade = Collections.max(allEvaluationsGrades);

        return maxEvaluationGrade >= grade;
    }
}
