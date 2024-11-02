package com.university;

import com.university.csv.CSVReader;
import com.university.csv.WriterCSV;
import com.university.course.Course;
import com.university.student.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        // Definir la ruta de entrada y salida
        String inputFilePath = "src/main/resources/input.csv";
        String outputFilePath = "src/main/resources/solution.csv";

        // Leer datos desde el archivo CSV y obtener un mapa de estudiantes con sus cursos
        Map<Student, List<Course>> studentCourseMap = CSVReader.readFromCSV(inputFilePath);

        // Extraer la lista de estudiantes únicos
        List<Student> uniqueStudents = new ArrayList<>(studentCourseMap.keySet());

        // Escribir los datos agregados en el archivo de salida
        WriterCSV.writeAggregatedData(outputFilePath, uniqueStudents);

        System.out.println("Archivo de salida generado exitosamente en: " + outputFilePath);
    }
}
