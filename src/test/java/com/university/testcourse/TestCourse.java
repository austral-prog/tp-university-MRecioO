package com.university.testcourse;

import com.university.entity.classroom.Course;
import com.university.entity.evaluation.types.FinalExam;
import com.university.entity.classroom.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCourse {

    @Test
    public void testCourseCreation() {
        Course course = new Course( "Math");
        course.addClassroom("101");
        course.addTeacher("Prof. Juan");

        assertEquals("101", course.listClassroom().get(0));
        assertEquals("Math", course.getSubject());
        assertEquals("Prof. Juan", course.listTeacher().getFirst());
        assertTrue(course.listStudents().isEmpty());
        assertTrue(course.listEvaluations().isEmpty());
    }

    @Test
    public void testAddClassroom() {
        Course course = new Course( "Math");
        course.addClassroom("101");
        assertEquals("101", course.listClassroom().get(0));
    }
    @Test
    public void testAddTeacher() {
        Course course = new Course( "Math");
        course.addTeacher("Prof. Juan");
        assertEquals("Prof. Juan", course.listTeacher().getFirst());
    }
    @Test
    public void addEvaluation() {
        Course course = new Course( "Math");
        Student student = new Student("Mati");
        FinalExam finalE = new FinalExam(student, course, "FINAL_PRACTICAL_WORK", "TP Final");

        assertTrue(course.listEvaluations().isEmpty());
        course.addEvaluation(finalE);
        assertTrue(course.listEvaluations().contains(finalE));
    }
    @Test
    public void testAddStudent() {
        Course course = new Course("Math");
        Student student = new Student("Mati");

        course.addStudent(student);

        assertEquals(1, course.getStudentCount());
        assertEquals(student, course.listStudents().get(0));
    }

    @Test
    public void testToString() {
        Course course = new Course( "Math");
        Student student = new Student("Mati");
        course.addStudent(student);
        course.addTeacher("Prof. Juan");
        student.addSubject(course);


        String expected = "Math,";
        assertEquals(expected, course.toStrCSV_2());
        assertEquals(expected, course.toStrCSV_3());
    }
    @Test
    void testEqualsAndHashCode() {
        Course course = new Course("Math");
        assertTrue(course.equals(course));

        assertFalse(course.equals(null));

        String notACourse = "This is not a Course";
        assertFalse(course.equals(notACourse));

        Course course2 = new Course("Math");
        assertTrue(course.equals(course2));

        Course course3 = new Course("Science");
        assertFalse(course.equals(course3));

        //hesh
        assertTrue(course.hashCode() == course.hashCode());
        assertFalse(course.hashCode() == course3.hashCode());
        assertTrue(course.hashCode() == course2.hashCode());
    }
}
