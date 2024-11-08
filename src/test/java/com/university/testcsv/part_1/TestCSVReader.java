package com.university.testcsv.part_1;
import com.university.entity.classroom.Course;
import com.university.csv_app.part_1.CSVReader;

import com.university.entity.classroom.Student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

public class TestCSVReader {

    @Test
    public void testReadFromCSV(){
        String testFilePath = "src/test/java/com/university/testcsv/part_1/test_reader.csv"; //Classroom,Subject,Student_Name,Student_Email,Subject_Teacher
                                                                                        //578,Political Science,Olivia Red,olivia.red@student.org,Prof. Sam
                                                                                        //331,Mathematics,Quincy Johnson,quincy.johnson@email.com,Prof. Hank

        Map<Student, List<Course>> sC = CSVReader.readFromCSV(testFilePath).getStudentCourseMap(); //{Quincy Johnson, 1=[Mathematics], Olivia Red, 1=[Political Science]}

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
    @Test
    public void testReadFromCSV_IOException() { // Al correr este test saldra un mensaje de error pero esta bien
        String invalidFilePath = "src/test/java/com/university/testcsv/non_existent_file.csv";

        // Al leer desde un archivo que no existe, debe retornar un mapa vacío
        Map<Student, List<Course>> result = CSVReader.readFromCSV(invalidFilePath).getStudentCourseMap();

        // Asegúrate de que el resultado sea un mapa vacío
        assertTrue(result.isEmpty(), "El mapa debería estar vacío cuando se produce una IOException.");
    }

}
