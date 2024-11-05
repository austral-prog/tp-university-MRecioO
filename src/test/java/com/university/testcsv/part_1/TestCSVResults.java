package com.university.testcsv.part_1;

import com.university.course.Course;
import com.university.csv.part_1.CSVReader;
import com.university.csv.part_1.CSVResult;
import com.university.student.Student;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import com.university.student.Student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCSVResults {

    @Test
    public void testGetters() {
        String testFilePath = "src/test/java/com/university/testcsv/part_1/test_reader.csv";
//        Classroom,Subject,Student_Name,Student_Email,Subject_Teacher
//        578,Political Science,Olivia Red,olivia.red@student.org,Prof. Sam
//        331,Mathematics,Quincy Johnson,quincy.johnson@email.com,Prof. Hank

        Map<String, Student> studentMap = CSVReader.readFromCSV(testFilePath).getStudentMap();// {Olivia Red=Olivia Red, Quincy Johnson=Quincy Johnson}
        Map<String, Course> courseMap = CSVReader.readFromCSV(testFilePath).getCourseMap();// {Mathematics=Mathematics, Political Science=Political Science}
        Map<Student, List<Course>> studentCourseMap = CSVReader.readFromCSV(testFilePath).getStudentCourseMap();//{Olivia Red=[Political Science], Quincy Johnson=[Mathematics]}

        assertEquals(new Student("Olivia Red"), studentMap.get("Olivia Red"));
        assertEquals(new Course("Political Science"), courseMap.get("Political Science"));
        assertEquals(new Course("Political Science"), studentCourseMap.get(new Student("Olivia Red")).getFirst());

        // Los atributos y metodos del estudiante se mantienen
        assertEquals("Olivia Red", studentMap.get("Olivia Red").getName());
        assertEquals(new Course("Political Science"), studentMap.get("Olivia Red").listSubjects().getFirst());

        // Del curso tambien
    }
}