package com.university.entity.evaluation.approval_criteria.types;

import com.university.entity.evaluation.approval_criteria.AbstractCriteria;
import com.university.entity.evaluation.Evaluation;

import java.util.ArrayList;
import java.util.List;

public class AverageAboveValue extends AbstractCriteria {

    public AverageAboveValue(String criteriaType, List<Evaluation> evaluationList, float grade){
        super(criteriaType,evaluationList, grade);
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

        // Sumar todas las calificaciones de las evaluaciones
        float suma = 0f;
        for (Float evaluationGrade : allEvaluationsGrades) {
            suma += evaluationGrade;
        }

        // Calcular el promedio de las calificaciones
        float averageEvaluationGrade = suma / evaluationList.size();

        // Devolver true si el promedio es mayor o igual que el valor de la calificación
        return averageEvaluationGrade >= grade;
    }
}
