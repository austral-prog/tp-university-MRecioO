package com.university.approval_criteria.types;

import com.university.approval_criteria.AbstractCriteria;
import com.university.evaluation.Evaluation;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class MinAboveValue extends AbstractCriteria {

    public MinAboveValue(String criteriaType, List<Evaluation> evaluationList, float grade) {
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
        float minEvaluationGrade = Collections.min(allEvaluationsGrades);

        return minEvaluationGrade >= grade;
    }

}
