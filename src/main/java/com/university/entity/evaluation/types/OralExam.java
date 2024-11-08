package com.university.entity.evaluation.types;

import com.university.entity.evaluation.Evaluation;
import com.university.entity.classroom.Student;
import com.university.entity.classroom.Course;

public class OralExam extends Evaluation {

    public OralExam(Student student, Course course, String evaluationType, String evaluationName) {
        super(student, course, evaluationType, evaluationName);
    }

    @Override
    public double calculateGrade() {
        return exercises.isEmpty() ? 0 : exercises.getFirst().getGrade();
    }
}
