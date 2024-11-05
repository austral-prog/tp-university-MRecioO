package com.university.testcsv.part_2;

import com.university.course.Course;
import com.university.csv.part_1.CSVReader;
import com.university.csv.part_2.CSVReader2;
import com.university.evaluation.Evaluation;
import com.university.evaluation.EvaluationFactory;
import com.university.evaluation.Exercise;
import com.university.student.Student;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCSVReader2 {
    @Test
    public void testRead2(){
       // Del Read1 para probar que mantienen los atributos del 1
        String testFilePath1 = "src/test/java/com/university/testcsv/part_1/test_reader.csv";
//        Classroom,Subject,Student_Name,Student_Email,Subject_Teacher
//        578,Political Science,Olivia Red,olivia.red@student.org,Prof. Sam
//        331,Mathematics,Quincy Johnson,quincy.johnson@email.com,Prof. Hank

        Map<String, Student> studentMap = CSVReader.readFromCSV(testFilePath1).getStudentMap();// {Olivia Red=Olivia Red, Quincy Johnson=Quincy Johnson}
        Map<String, Course> courseMap = CSVReader.readFromCSV(testFilePath1).getCourseMap();// {Mathematics=Mathematics, Political Science=Political Science}

        //

        String testFilePath2 = "src/test/java/com/university/testcsv/part_2/test_reader2.csv";

        Map<Evaluation, List<Exercise>> evalExerciseMap = CSVReader2.read2(testFilePath2, studentMap, courseMap).getEvalExerciseMap();

        assertTrue(evalExerciseMap.size() == 11);

        for (Map.Entry<Evaluation, List<Exercise>> entry : evalExerciseMap.entrySet()) {
            List<Exercise> exerciseListFor = entry.getValue();
            assertTrue(!exerciseListFor.isEmpty());
        }

        // test usando CSVResults2 + CSVRead1 para las listas del Read2
        Map<String, Student> studentMap2 = CSVReader2.read2(testFilePath2, studentMap, courseMap).getStudentMap();
        assertTrue(studentMap2.size() == 2, "Olivia y Johnson");
        assertEquals("olivia.red@student.org", studentMap2.get("Olivia Red").listEmail().getFirst(), "Debe conservar TODOS los atrivutos del input_1.");
        Map<String, Course> courseMap2 =CSVReader2.read2(testFilePath2, studentMap, courseMap).getCourseMap();
        assertEquals("Prof. Sam", courseMap2.get("Political Science").listTeacher().getFirst(), "Debe conservar TODOS los atrivutos del input_1.");
        Map<String, Evaluation> evaluationMap2= CSVReader2.read2(testFilePath2, studentMap, courseMap).getEvaluationMap();
        assertTrue(evaluationMap2.size() == 11, "evaluationMap2");
        Map<Evaluation, List<Exercise>> evalExerciseMap2 = evalExerciseMap;
        for (Map.Entry<Evaluation, List<Exercise>> entry : evalExerciseMap2.entrySet()) {
            List<Exercise> exerciseListFor = entry.getValue();
            assertTrue(!exerciseListFor.isEmpty());
        }
    }
}
