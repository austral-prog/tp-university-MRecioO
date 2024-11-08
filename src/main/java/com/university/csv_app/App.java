package com.university.csv_app;

import com.university.entity.evaluation.approval_criteria.AbstractCriteria;
import com.university.csv_app.part_1.*;
import com.university.csv_app.part_2.*;
import com.university.csv_app.part_3.*;

import com.university.entity.classroom.Course;
import com.university.csv_app.part_3.CSVReader3;
import com.university.entity.evaluation.Exercise;
import com.university.entity.classroom.Student;
import com.university.entity.evaluation.Evaluation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws IOException {
        // Definir las rutas de entrada y salida
        String inputFilePath1 = "src/main/resources/input.csv";  // Archivo de entrada de la parte 1
        String outputFilePath1 = "src/main/resources/solution.csv";  // Archivo de salida de la parte 1
        String inputFilePath2 = "src/main/resources/input_2.csv";  // Archivo de entrada de la parte 2
        String outputFilePath2 = "src/main/resources/solution2.csv";  // Archivo de salida de la parte 2
        String inputFilePath3 = "src/main/resources/input_3.csv";  // Archivo de entrada de la parte 3
        String outputFilePath3_raw = "src/main/resources/solution3_RAW.csv";  // Archivo de salida de la parte 3 RAW
        String outputFilePath3 = "src/main/resources/solution3.csv";  // Archivo de salida de la parte 3

        // ====== PARTE 1 ======
        // Leer datos desde el archivo CSV de la parte 1 y obtener un mapa de estudiantes con sus cursos
        CSVResult readResult1 = CSVReader.readFromCSV(inputFilePath1);

        Map<Student, List<Course>> studentCourseMap = readResult1.getStudentCourseMap();


        // Extraer la lista de estudiantes únicos
        List<Student> uniqueStudents = new ArrayList<>(studentCourseMap.keySet());

        // Escribir los datos agregados en el archivo de salida de la parte 1
        WriterCSV.writeAggregatedData(outputFilePath1, uniqueStudents);
        System.out.println("Archivo de salida generado exitosamente en: " + outputFilePath1);

        // ====== PARTE 2 ======
        // Obtiene los mapas de la primera parte para no perder los datos anteriores y almacenar los estudiantes y cursos leídos en ambas partes


        Map<String, Student> studentMap = readResult1.getStudentMap();
        Map<String, Course> courseMap = readResult1.getCourseMap();

        // Leer datos desde el archivo CSV de la parte 2 y obtener un mapa de evaluaciones con ejercicios
        CSVResult2 reedResult2 = CSVReader2.read2(inputFilePath2, studentMap, courseMap);
        Map<String, Evaluation> evaluationMap = reedResult2.getEvaluationMap();

        // Escribir los datos procesados en el archivo de salida de la parte 2
        WriterCSV2.writeSolution(outputFilePath2, evaluationMap);
        System.out.println("Archivo de salida generado exitosamente en: " + outputFilePath2);

        // ====== PARTE 3 ======
        // Obtiene un mapa de cursos obtenido actualizado en la segunda parte
        Map<String, Student> actualStudentMap = reedResult2.getStudentMap();
        Map<Evaluation, List<Exercise>> actualEvaluationtMap = reedResult2.getEvalExerciseMap();

        CSVResult3 readResult3 = CSVReader3.readCriteria(inputFilePath3, actualEvaluationtMap, actualStudentMap);
        Map<String, AbstractCriteria> criteriaMap = readResult3.getCriteriaMap();

        WriterCSV3_RAW.writeApprovalResults(outputFilePath3_raw, criteriaMap);
        System.out.println("Archivo de salida generado exitosamente en: " + outputFilePath3_raw);


        Map<String, List<AbstractCriteria>> criteriaListMap = readResult3.getCriteriaListMap();
        WriteCSV3.writeSubjectApprovalReport(outputFilePath3, criteriaListMap);
        System.out.println("Archivo de salida generado exitosamente en: " + outputFilePath3);

    }
}