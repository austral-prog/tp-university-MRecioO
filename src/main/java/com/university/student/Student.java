package com.university.student;

import com.university.course.Course;
import com.university.csv.part_1.ToStrCSV_1;
import com.university.csv.part_2.ToStrCSV_2;
import com.university.csv.part_3.ToStrCSV_3;
import com.university.evaluation.Evaluation;

import java.util.ArrayList;
import java.util.List;
public class Student implements ToStrCSV_1<Student>, ToStrCSV_2<Student>, ToStrCSV_3<Student> {
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

    public List<Course> listSubjects() {
        return subjects;
    }
    public List<Evaluation> listEvaluations() {
        return evaluations;
    }

    public Evaluation findEvaluationsByNameAndCourse(String evaluationName, Course course) {

        for (Evaluation eval : evaluations) {
            if (eval.getEvaluationName().equalsIgnoreCase(evaluationName) && eval.getCourse().equals(course)) {
               return eval;
            }
        }
        return null;
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
    public String toStrCSV_1() {
        return name + "," + subjects.size() ;
    }
    @Override
    public String toStrCSV_2() {
        return name + "," ;
    }
    @Override
    public String toStrCSV_3(){
        return toStrCSV_2() ;
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