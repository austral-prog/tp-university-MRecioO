package com.university.entity.classroom;

import com.university.csv_app.part_2.ToStrCSV_2;
import com.university.csv_app.part_3.ToStrCSV_3;
import com.university.cli.entityCRUD.Entity;
import com.university.entity.evaluation.Evaluation;

import java.util.ArrayList;
import java.util.List;

public class Course implements ToStrCSV_2<Course>, ToStrCSV_3<Course>, Entity {
    private List<String> classroom;
    private String subject;
    private List<String> teacher;
    private List<Student> students;
    private List<Evaluation> evaluations;
    private int id;
    private static int idCounter = 0;

    // Constructor
    public Course(String subject) {
        this.classroom = new ArrayList<>();
        this.subject = subject;
        this.teacher = new ArrayList<>();
        this.students = new ArrayList<>();
        this.evaluations = new ArrayList<>();
        this.id = ++idCounter;
    }
    public Course() {
        this.classroom = new ArrayList<>();
        this.subject = subject;
        this.teacher = new ArrayList<>();
        this.students = new ArrayList<>();
        this.evaluations = new ArrayList<>();
        this.id = ++idCounter;
    }

    // Adders
    public void addEvaluation(Evaluation evaluation) {
        this.evaluations.add(evaluation);
    }
    public void addStudent(Student student) {
        students.add(student);
    }
    public void addClassroom(String classroom) {
        this.classroom.add(classroom);
    }
    public void addTeacher(String teacher) {
        this.teacher.add(teacher);
    }

    // List y getters
    public List<Evaluation> listEvaluations() {
        return evaluations;
    }

    public List<String> listClassroom() {
        return classroom;
    }

    public String getSubject() {
        return subject;
    }

    public List<String> listTeacher() {
        return teacher;
    }

    public List<Student> listStudents() {
        return students;
    }

    public int getStudentCount() {
        return students.size();
    }

    // Removers
    public boolean removeClassroom(String classroom) {
        return this.classroom.remove(classroom);
    }
    public boolean removeTeacher(String teacher) {
        return this.teacher.remove(teacher);
    }
    public boolean removeStudent(Student student) {
        return this.students.remove(student);
    }
    public boolean removeEvaluation(Evaluation evaluation) {
        return this.evaluations.remove(evaluation);
    }

    @Override
    public int getId(){
        return id;
    }

    // Sertt
    @Override
    public void setId(int id) {
        this.id = id;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }


    @Override
    public String toString(){
        return this.subject;
    }
    @Override
    public String toStrCSV_2() {
        return subject + ",";
    }
    @Override
    public String toStrCSV_3() {
        return toStrCSV_2();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return subject.equals(course.subject);
    }

    @Override
    public int hashCode() {
        return subject.hashCode();
    }
}
