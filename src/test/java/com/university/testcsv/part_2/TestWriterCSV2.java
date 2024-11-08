package com.university.testcsv.part_2;

import com.university.entity.classroom.Course;
import com.university.csv_app.part_2.WriterCSV2;
import com.university.entity.evaluation.Evaluation;
import com.university.entity.evaluation.Exercise;
import com.university.entity.evaluation.types.FinalExam;
import com.university.entity.classroom.Student;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestWriterCSV2 {

    @Test
    public void testWriteSolution() throws IOException {
        String outputPath = "src/test/java/com/university/testcsv/part_2/expected2.csv";

        // Crear mapa de prueba con evaluaciones
        Student student = new Student("Alice");
        Course course = new Course("Art");
        Evaluation evaluation = new FinalExam(student, course, "FINAL_EXAM", "TP Final");
        evaluation.addExercise(new Exercise("Exercise1", 2240.999999999999f)); ///Se tendira que redondear y tener un solo decimal

        // Crear mapa de prueba con evaluaciones
        Student student2 = new Student("Mati");
        Course course2 = new Course("AAAAA"); //// Tendria que estar primero en el expected
        Evaluation evaluation2 = new FinalExam(student2, course2, "FINAL_EXAM", "TP Final");
        evaluation2.addExercise(new Exercise("Exercise1", 2.2498999f)); /// Se tendria que redomdear y tener como maximo dos decimales

        Map<String, Evaluation> evaluationMap = new HashMap<>();
        evaluationMap.put("ArtTP FinalAlice", evaluation);
        evaluationMap.put("ArtTP FinalMati", evaluation2);

        // Ejecutar el metodo de escritura
        WriterCSV2.writeSolution(outputPath, evaluationMap);

        // Leer el archivo y verificar contenido
        List<String> lines = Files.readAllLines(Path.of(outputPath));

        // Comprobar encabezado y primera línea de datos
        assertEquals("Subject_Name,Evaluation_Name,Student_Name,Grade", lines.get(0));
        assertEquals("AAAAA,TP Final,Mati,2.25", lines.get(1));
        assertEquals("Art,TP Final,Alice,2241.0", lines.get(2));
        // Verificar que no haya línea extra
        assertEquals(3, lines.size());
    }
}
