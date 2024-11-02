package com.university.course;

import com.university.evaluation.Evaluation;
import com.university.student.Student;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private List<String> classroom;
    private String subject;
    private List<String> teacher;
    private List<Student> students;
    private List<Evaluation> evaluations;

    // Constructor
    public Course(String subject) {
        this.classroom = new ArrayList<>();
        this.subject = subject;
        this.teacher = new ArrayList<>();
        this.students = new ArrayList<>();
        this.evaluations = new ArrayList<>();
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

    @Override
    public String toString() {
        return subject;
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
