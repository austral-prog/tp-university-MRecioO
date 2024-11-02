package com.university.evaluation;

import com.university.student.Student;
import com.university.course.Course;
import java.util.ArrayList;
import java.util.List;

public abstract class Evaluation {
    protected Student student;
    protected Course course;
    protected String evaluationType;
    protected String evaluationName;
    protected List<Exercise> exercises;

    public Evaluation(Student student, Course course, String evaluationType, String evaluationName) {
        this.student = student;
        this.course = course;
        this.evaluationType = evaluationType;
        this.evaluationName = evaluationName;
        this.exercises = new ArrayList<>();
    }

    public abstract double calculateGrade();


    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    // Getters
    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getEvaluationType() {
        return evaluationType;
    }

    public String getEvaluationName() {
        return evaluationName;
    }

    public List<Exercise> listExercises() {
        return exercises;
    }
}
