package com.university.evaluation.types;

import com.university.evaluation.Evaluation;
import com.university.student.Student;
import com.university.course.Course;

public class OralExam extends Evaluation {

    public OralExam(Student student, Course course, String evaluationType, String evaluationName) {
        super(student, course, evaluationType, evaluationName);
    }

    @Override
    public double calculateGrade() {
        return exercises.isEmpty() ? 0 : exercises.getFirst().getGrade();
    }
}
