package com.university.csv;

import com.university.student.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriterCSV {

    public static void writeAggregatedData(String filePath, List<Student> students) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Escribir encabezado
            writer.append("Student_Name,Course_Count");

            // Ordenar los estudiantes alfabéticamente por nombre
            students.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));

            // Escribir datos de cada estudiante
            for (Student student : students) {
                writer.append("\n")
                        .append(student.getName()).append(",")
                        .append(String.valueOf(student.getSubjects().size()));
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo CSV: " + filePath);
            e.printStackTrace();
        }
    }
}
