package com.university.testcourse;

import com.university.course.Course;
import com.university.student.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        //course.addEvaluation();
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


        String expected = "Math";
        assertEquals(expected, course.toString());
    }
}
