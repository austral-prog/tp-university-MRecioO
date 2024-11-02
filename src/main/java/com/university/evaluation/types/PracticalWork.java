package com.university.evaluation.types;

import com.university.evaluation.Evaluation;
import com.university.student.Student;
import com.university.course.Course;

public class PracticalWork extends Evaluation {

    public PracticalWork(Student student, Course course, String evaluationType, String evaluationName) {
        super(student, course, evaluationType, evaluationName);
    }

    @Override
    public double calculateGrade() {
        return exercises.isEmpty() ? 0 : exercises.get(exercises.size() - 1).getGrade();
    }
}
