package com.university.teststudents;

import com.university.course.Course;
import com.university.student.Student;
import org.junit.jupiter.api.Test;
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
        assertEquals(c, s.getSubjects().getFirst());
    }


    @Test
    public void testToString() {
        Student s = new Student("Mati");

        String expected = "Mati, 0";
        assertEquals(expected, s.toString());

        Course c = new Course( "Math");
        s.addSubject(c);

        String expected2 = "Mati, 1";
        assertEquals(expected2, s.toString());
    }
}
