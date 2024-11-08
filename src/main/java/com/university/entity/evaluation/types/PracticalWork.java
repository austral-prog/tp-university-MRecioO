package com.university.entity.evaluation.types;

import com.university.entity.evaluation.Evaluation;
import com.university.entity.classroom.Student;
import com.university.entity.classroom.Course;

public class PracticalWork extends Evaluation {

    public PracticalWork(Student student, Course course, String evaluationType, String evaluationName) {
        super(student, course, evaluationType, evaluationName);
    }

    @Override
    public double calculateGrade() {
        return exercises.isEmpty() ? 0 : exercises.get(exercises.size() - 1).getGrade();
    }
}
