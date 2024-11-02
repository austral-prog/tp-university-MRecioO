package com.university.evaluation.types;

import com.university.evaluation.Evaluation;
import com.university.evaluation.Exercise;
import com.university.student.Student;
import com.university.course.Course;

public class WrittenExam extends Evaluation {

    public WrittenExam(Student student, Course course, String evaluationType, String evaluationName) {
        super(student, course, evaluationType, evaluationName);
    }

    @Override
    public double calculateGrade() {
        return exercises.stream()
                .mapToDouble(Exercise::getGrade)
                .average()
                .orElse(0);
    }
}
