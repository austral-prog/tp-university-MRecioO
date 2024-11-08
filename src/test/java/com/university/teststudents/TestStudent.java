package com.university.teststudents;

import com.university.entity.classroom.Course;
import com.university.entity.evaluation.Evaluation;
import com.university.entity.evaluation.types.EvaluationFactory;
import com.university.entity.classroom.Student;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestStudent {

    @Test
    public void testStudentCreation() {
        Student s = new Student("Mati");
        Course c = new Course( "Math");
        s.addEmail("mati@gmail.com");
        s.addSubject(c);

        assertEquals("Mati", s.getName());
        assertEquals("mati@gmail.com", s.listEmail().get(0));
        assertEquals(c, s.listSubjects().getFirst());
        assertTrue(s.listEvaluations().isEmpty());
    }


    @Test
    public void testToString() {
        Student s = new Student("Mati");

        assertEquals("Mati,0", s.toStrCSV_1());

        assertTrue("Mati,".equals(s.toStrCSV_2()));
        assertTrue(s.toStrCSV_2().equals(s.toStrCSV_3()));

    }
    @Test
    public void testAdders(){
        Student s = new Student("Mati");
        Course c = new Course("Math");
        Evaluation eval = EvaluationFactory.createEvaluation(s, c, "FINAL_PRACTICAL_WORK", "TP Final");
        s.addEmail("mati@gmail.com");
        s.addSubject(c);
        s.addEvaluation(eval);
        assertTrue(s.listEvaluations().size() == 1 && s.listSubjects().size() == 1 && s.listEmail().size() == 1);
    }
    @Test
    void testEqualsAndHashCode() {
        Student student = new Student("Mati");
        assertTrue(student.equals(student));

        assertFalse(student.equals(null));

        String notAStudent = "Matint";
        assertFalse(student.equals(notAStudent));


        Student student2 = new Student("Mati");
        assertTrue(student.equals(student2));


        Student student3 = new Student("Alice");
        assertFalse(student.equals(student3));

        //hesh
        assertTrue(student.hashCode() == student.hashCode());
        assertFalse(student.hashCode() == student3.hashCode());
        assertTrue(student.hashCode() == student2.hashCode());
        Map<String, Student> students = new HashMap<>();
    }
}
