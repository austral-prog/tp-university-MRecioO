package com.university.evaluation.types;

import com.university.evaluation.Evaluation;
import com.university.student.Student;
import com.university.course.Course;

public class EvaluationFactory {

    public static Evaluation createEvaluation(Student student, Course course, String evaluationType, String evaluationName) {
        switch (evaluationType) {
            case "FINAL_PRACTICAL_WORK":
                return new FinalExam(student, course, evaluationType, evaluationName);
            case "PRACTICAL_WORK":
                return new PracticalWork(student, course, evaluationType, evaluationName);
            case "ORAL_EXAM":
                return new OralExam(student, course, evaluationType, evaluationName);
            case "WRITTEN_EXAM":
                return new WrittenExam(student, course, evaluationType, evaluationName);
            default:
                throw new IllegalArgumentException("Tipo de evaluación no reconocido: " + evaluationType);
        }
    }
}
