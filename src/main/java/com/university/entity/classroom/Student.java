package com.university.entity.classroom;

import com.university.csv_app.part_1.ToStrCSV_1;
import com.university.csv_app.part_2.ToStrCSV_2;
import com.university.csv_app.part_3.ToStrCSV_3;
import com.university.cli.entityCRUD.Entity;
import com.university.entity.evaluation.Evaluation;

import java.util.ArrayList;
import java.util.List;
public class Student implements ToStrCSV_1<Student>, ToStrCSV_2<Student>, ToStrCSV_3<Student>, Entity {
    private String name;
    private List<String> email;
    private List<Course> subjects;
    private List<Evaluation> evaluations;
    private int id;
    private static int idCounter = 0;

    // Constructor
    public Student(String name) {
        this.name = name;
        this.email = new ArrayList<String>();
        this.subjects = new ArrayList<Course>();
        this.evaluations = new ArrayList<>();
        this.id = ++ idCounter;
    }
    public Student() {
        this.name = name;
        this.email = new ArrayList<String>();
        this.subjects = new ArrayList<Course>();
        this.evaluations = new ArrayList<>();
        this.id = ++ idCounter;
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
    public void addEvaluation(Evaluation evaluation) {
        this.evaluations.add(evaluation);
    }

    // Removers
    public boolean removeEmail(String email) {
        return this.email.remove(email);
    }
    public boolean removeCourse(Course course) {
        return this.subjects.remove(course);
    }
    public boolean removeEvaluation(Evaluation evaluation) {
        return this.evaluations.remove(evaluation);
    }

    // setters
    public void setName(String name){
        this.name = name;
    }
    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }



    @Override
    public String toString(){
        return this.name;
    }
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