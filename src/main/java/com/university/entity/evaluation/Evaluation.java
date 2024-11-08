package com.university.entity.evaluation;

import com.university.csv_app.part_2.ToStrCSV_2;
import com.university.cli.entityCRUD.Entity;
import com.university.entity.classroom.Student;
import com.university.entity.classroom.Course;
import java.util.ArrayList;
import java.util.List;

public abstract class Evaluation implements ToStrCSV_2<Evaluation> , Entity {
    protected Student student;
    protected Course course;
    protected String evaluationType;
    protected String evaluationName;
    protected List<Exercise> exercises;
    private static int idCounter = 0;
    private int id;

    public Evaluation(Student student, Course course, String evaluationType, String evaluationName) {
        this.student = student;
        this.course = course;
        this.evaluationType = evaluationType;
        this.evaluationName = evaluationName;
        this.exercises = new ArrayList<>();
        this.id = ++ idCounter;
    }
    public Evaluation(){

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

    public int getId(){
        return this.id;
    }

    // Setters
    public void setId(int id){
        this.id = id;
    }
    public void setStudent(Student student){
        this.student = student;
    }
    public void setCourse(Course course){
        this.course = course;
    }
    public void setEvaluationType(String evaluationType){
        this.evaluationType = evaluationType;
    }
    public void setEvaluationName(String evaluationName){
        this.evaluationName = evaluationName;
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
    @Override
    public String toString() {
        return evaluationName ;
    }

}
