package com.university.student;

import com.university.course.Course;
import com.university.evaluation.Evaluation;

import java.util.ArrayList;
import java.util.List;
public class Student {
    private String name;
    private List<String> email;
    private List<Course> subjects;
    private List<Evaluation> evaluations;

    // Constructor
    public Student(String name) {
        this.name = name;
        this.email = new ArrayList<String>();
        this.subjects = new ArrayList<Course>();
        this.evaluations = new ArrayList<>();
    }

    // Getters y list
    public String getName() {
        return name;
    }

    public List<String> listEmail() {
        return email;
    }

    public List<Course> getSubjects() {
        return subjects;
    }
    public List<Evaluation> listEvaluations() {
        return evaluations;
    }

    // ADders
    public void addEmail(String email) {
        this.email.add(email);
    }
    public void addSubject(Course subject) {
        subjects.add(subject);
    }
    public void addEvaluation(Evaluation evaluation) {this.evaluations.add(evaluation);}



    @Override
    public String toString() {
        return name + ", " + subjects.size();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return name.equals(student.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}