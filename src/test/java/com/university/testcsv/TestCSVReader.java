package com.university.testcsv;
import com.university.course.Course;
import com.university.csv.CSVReader;

import com.university.student.Student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestCSVReader {

    @Test
    public void testReadFromCSV(){
        String testFilePath = "src/test/java/com/university/testcsv/test_students.csv"; //Classroom,Subject,Student_Name,Student_Email,Subject_Teacher
                                                                                        //578,Political Science,Olivia Red,olivia.red@student.org,Prof. Sam
                                                                                        //331,Mathematics,Quincy Johnson,quincy.johnson@email.com,Prof. Hank

        Map<Student, List<Course>> sC = CSVReader.readFromCSV(testFilePath); //{Quincy Johnson, 1=[Mathematics], Olivia Red, 1=[Political Science]}

        assertEquals(2, sC.size());

        sC.forEach((student, courses) -> {
            assertFalse(courses.isEmpty(), "Cada estudiante debe tener al menos un curso.");
            courses.forEach(course -> assertNotNull(course.getSubject()));
        });

        Student olivia = new Student("Olivia Red");
        assertTrue(sC.containsKey(olivia));

        List<Course> oliviaCourses = sC.get(olivia);
        assertEquals(1, oliviaCourses.size());

        Course courseCompare = new Course("Political Science");
        assertEquals(courseCompare, oliviaCourses.get(0));


        //Courses attributes changes
        assertEquals("578", oliviaCourses.getFirst().listClassroom().getFirst());
        assertEquals("Prof. Sam", oliviaCourses.getFirst().listTeacher().getFirst());
        assertEquals(olivia, oliviaCourses.getFirst().listStudents().getFirst());

        //Student attributes changes
        Student studentOlivia = sC.keySet().stream().toList().get(0);
        assertEquals("Olivia Red", studentOlivia.getName());
        assertEquals("olivia.red@student.org", studentOlivia.listEmail().getFirst());
        assertEquals(oliviaCourses, sC.get(studentOlivia));
    }
}
