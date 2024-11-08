package com.university.testevaluation;


import com.university.entity.evaluation.Evaluation;
import com.university.entity.evaluation.Exercise;
import com.university.entity.evaluation.types.*;
import com.university.entity.classroom.Course;
import com.university.entity.classroom.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCreateEvaluation {

    private Student student;
    private Course course;

    @BeforeEach
    void setUp() {
        student = new Student("Mati");
        course = new Course("Prog");
    }
    @Test
    void testGettersListAndAdders(){
        Evaluation eval = EvaluationFactory.createEvaluation(student, course, "FINAL_PRACTICAL_WORK", "TP Final");
        assertEquals(student, eval.getStudent());
        assertEquals(course, eval.getCourse());
        assertEquals("FINAL_PRACTICAL_WORK", eval.getEvaluationType());
        assertEquals("TP Final", eval.getEvaluationName());

        Exercise exercise = new Exercise("TP Final", 4);
        assertTrue(eval.listExercises().isEmpty());
        eval.addExercise(exercise);
        assertEquals(exercise, eval.listExercises().get(0));
    }
    // Types
    @Test
    void testCreateFinalExam() {
        Evaluation evaluation = EvaluationFactory.createEvaluation(student, course, "FINAL_PRACTICAL_WORK", "TP Final");
        assertTrue(evaluation instanceof FinalExam);
        assertEquals("TP Final", evaluation.getEvaluationName());
    }

    @Test
    void testCreatePracticalWork() {
        Evaluation evaluation = EvaluationFactory.createEvaluation(student, course,"PRACTICAL_WORK", "TP1");
        assertTrue(evaluation instanceof PracticalWork);
        assertEquals("TP1", evaluation.getEvaluationName());
    }

    @Test
    void testCreateOralExam() {
        Evaluation evaluation = EvaluationFactory.createEvaluation(student, course,"ORAL_EXAM", "Primer Parcial");
        assertTrue(evaluation instanceof OralExam);
        assertEquals("Primer Parcial", evaluation.getEvaluationName());
    }

    @Test
    void testCreateWrittenExam() {
        Evaluation evaluation = EvaluationFactory.createEvaluation(student, course,"WRITTEN_EXAM", "Segundo Parcial");
        assertTrue(evaluation instanceof WrittenExam);
        assertEquals("Segundo Parcial", evaluation.getEvaluationName());
    }

    @Test
    void testInvalidEvaluationType() {
        assertThrows(IllegalArgumentException.class, () ->
                EvaluationFactory.createEvaluation(student, course, "InvalidType", "Invalid Exam"));
    }
}
