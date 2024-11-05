package com.university;

import com.university.csv.part_1.*;
import com.university.csv.part_2.*;
import com.university.course.Course;
import com.university.student.Student;
import com.university.evaluation.Evaluation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        // Definir las rutas de entrada y salida
        String inputFilePath1 = "src/main/resources/input.csv";  // Archivo de entrada de la parte 1
        String outputFilePath1 = "src/main/resources/solution.csv";  // Archivo de salida de la parte 1
        String inputFilePath2 = "src/main/resources/input_2.csv";  // Archivo de entrada de la parte 2
        String outputFilePath2 = "src/main/resources/solution2.csv";  // Archivo de salida de la parte 2

        // Llamar a los métodos para procesar las dos partes
        processPart1(inputFilePath1, outputFilePath1);
        processPart2(inputFilePath2, outputFilePath2);
    }

    // Método para procesar la PARTE 1
    private static void processPart1(String inputFilePath, String outputFilePath) {
        // Leer datos desde el archivo CSV de la parte 1 y obtener un mapa de estudiantes con sus cursos
        Map<Student, List<Course>> studentCourseMap = CSVReader.readFromCSV(inputFilePath).getStudentCourseMap();

        // Extraer la lista de estudiantes únicos
        List<Student> uniqueStudents = new ArrayList<>(studentCourseMap.keySet());

        // Escribir los datos agregados en el archivo de salida de la parte 1
        WriterCSV.writeAggregatedData(outputFilePath, uniqueStudents);
        System.out.println("Archivo de salida generado exitosamente en: " + outputFilePath);
    }

    // Método para procesar la PARTE 2
    private static void processPart2(String inputFilePath, String outputFilePath) {
        // Crear mapas para almacenar los estudiantes y cursos leídos en ambas partes
        Map<String, Student> studentMap = new HashMap<>();
        Map<String, Course> courseMap = new HashMap<>();

        // Leer datos desde el archivo CSV de la parte 2 y obtener un mapa de evaluaciones con ejercicios
        Map<String, Evaluation> evaluationMap = CSVReader2.read2(inputFilePath, studentMap, courseMap).getEvaluationMap();

        // Escribir los datos procesados en el archivo de salida de la parte 2
        WriterCSV2.writeSolution(outputFilePath, evaluationMap);
        System.out.println("Archivo de salida generado exitosamente en: " + outputFilePath);
    }
}
