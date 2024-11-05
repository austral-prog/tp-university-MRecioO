package com.university.evaluation;

import com.university.csv.part_2.ToStrCSV_2;
import com.university.student.Student;
import com.university.course.Course;
import java.util.ArrayList;
import java.util.List;

public abstract class Evaluation implements ToStrCSV_2<Evaluation> {
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
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Evaluation other = (Evaluation) obj;

        // Comparamos cada atributo excepto 'exercises'
        return student.equals(other.student) &&
                course.equals(other.course) &&
                evaluationType.equals(other.evaluationType) &&
                evaluationName.equals(other.evaluationName);
    }
    @Override
    public String toStrCSV_2() {
        return evaluationName + ",";
    }

}
