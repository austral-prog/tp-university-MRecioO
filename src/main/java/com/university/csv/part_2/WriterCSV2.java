package com.university.csv.part_2;

import com.university.course.Course;
import com.university.evaluation.Evaluation;
import com.university.student.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class WriterCSV2 {

    public static void writeSolution(String filePath, Map<String, Evaluation> evaluationMap) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Escribir encabezado
            writer.append("Subject_Name,Evaluation_Name,Student_Name,Grade\n");


            // Ordenar las evaluaciones por nombre de materia, nombre de evaluación y nombre de estudiante
            evaluationMap.values().stream()
                    .sorted((eval1, eval2) -> {
                        int courseCompare = eval1.getCourse().getSubject().compareToIgnoreCase(eval2.getCourse().getSubject());
                        if (courseCompare != 0) return courseCompare;

                        int evalNameCompare = eval1.getEvaluationName().compareToIgnoreCase(eval2.getEvaluationName());
                        if (evalNameCompare != 0) return evalNameCompare;

                        int studentNameCompare = eval1.getStudent().getName().compareToIgnoreCase(eval2.getStudent().getName());
                        if (studentNameCompare != 0) return studentNameCompare;

                        return -1;

                    })
                    .forEach(evaluation -> {

                        double grade = evaluation.calculateGrade();

                        try {
                            writer.append(evaluation.getCourse().toStrCSV_2())
                                    .append(evaluation.toStrCSV_2())
                                    .append(evaluation.getStudent().toStrCSV_2());
                            // Formato del grade: si es entero, mostrar sin decimales; si no, mostrar dos decimales
                            if (grade % 1 == 0 || String.valueOf(grade).length() <= 3 ) {
                                writer.append(String.format(Locale.US, "%.1f", grade)); // Convertir a entero si el valor es como 2.00
                            } else {
                                writer.append(String.format(Locale.US, "%.2f", grade)); // Usar punto decimal para flotantes
                            }

                            writer.append("\n");
                        } catch (IOException e) {
                            System.err.println("Error al escribir la línea para la evaluación: " + evaluation);
                            e.printStackTrace();
                        }
                    });

        } catch (IOException e) {
            System.err.println("Error al escribir el archivo CSV: " + filePath);
            e.printStackTrace();
        }
    }
}
