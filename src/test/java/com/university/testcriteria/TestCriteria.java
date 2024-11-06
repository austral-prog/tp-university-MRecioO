package com.university.testcriteria;

import com.university.approval_criteria.AbstractCriteria;
import com.university.approval_criteria.types.CriteriaFactory;
import com.university.course.Course;
import com.university.evaluation.Evaluation;
import com.university.evaluation.Exercise;
import com.university.evaluation.types.FinalExam;
import com.university.evaluation.types.OralExam;
import com.university.evaluation.types.PracticalWork;
import com.university.evaluation.types.WrittenExam;
import com.university.student.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCriteria {
    private Student student;
    private Course course;
    private OralExam oralExam;
    private OralExam oralExam2;
    private OralExam oralExam3;
    private OralExam oralExam4;
    private OralExam oralExam5;
    private AbstractCriteria cA;
    private AbstractCriteria cMax;
    private AbstractCriteria cMin;


    private List<Evaluation> evaluations = new ArrayList<>();

    @BeforeEach
    void setUp() {
        student = new Student("Alice Azure");
        course = new Course("Computer Science");

        oralExam = new OralExam(student, course, "ORAL_EXAM", "1");
        oralExam2 = new OralExam(student, course, "ORAL_EXAM", "2");
        oralExam3 = new OralExam(student, course, "ORAL_EXAM", "3");
        oralExam4 = new OralExam(student, course, "ORAL_EXAM", "4");
        oralExam5 = new OralExam(student, course, "ORAL_EXAM", "5");



        oralExam.addExercise(new Exercise("Ej1", 10));
        evaluations.add(oralExam);
        oralExam2.addExercise(new Exercise("Ej1", 5));
        evaluations.add(oralExam2);
        oralExam3.addExercise(new Exercise("Ej1", 5));
        evaluations.add(oralExam3);
        oralExam4.addExercise(new Exercise("Ej1", 4));
        evaluations.add(oralExam4);


        // evaluation str [1 Grade: 10.0, 2 Grade: 5.0, 3 Grade: 5.0, 4 Grade: 4.0]

        cA = CriteriaFactory.createCriteria("AVERAGE_ABOVE_VALUE",  evaluations, 6f);//actual evaluations average = 6
        cMax = CriteriaFactory.createCriteria("MAX_ABOVE_VALUE",  evaluations, 10f);//actual evaluations max = 10
        cMin = CriteriaFactory.createCriteria("MIN_ABOVE_VALUE",  evaluations, 4f);//actual evaluations min =4



    }
    @Test
    public void testApproved() {
        assertTrue(cA.isApproved());
        assertTrue(cMax.isApproved());
        assertTrue(cMin.isApproved());
    }
    @Test
    public void testNotApproved() {
        cA.listEvaluations().remove(oralExam); // saca el oral de nota 10
        assertFalse(cA.isApproved());//actual evaluation average 4.5
        assertFalse(cMax.isApproved());//actual evaluations max = 5
        assertTrue(cMin.isApproved());// no cambia

        oralExam5.addExercise(new Exercise("Ej1", 2));
        cA.listEvaluations().add(oralExam5);// agrega otra evaluacion con nota 2 para desaprobar 
        assertFalse(cMin.isApproved());
    }
}
