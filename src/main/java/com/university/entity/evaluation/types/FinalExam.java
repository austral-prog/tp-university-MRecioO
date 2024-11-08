package com.university.entity.evaluation.types;

import com.university.entity.evaluation.Evaluation;
import com.university.entity.evaluation.Exercise;
import com.university.entity.classroom.Student;
import com.university.entity.classroom.Course;


public class FinalExam extends Evaluation {
    public FinalExam(Student student, Course course, String evaluationType, String evaluationName) {
        super(student, course, evaluationType, evaluationName);
    }

    @Override
    public double calculateGrade() {
        return exercises.stream().mapToDouble(Exercise::getGrade).sum();
    }
}
