package com.university.testcsv.part_1;


import com.university.entity.classroom.Course;
import com.university.csv_app.part_1.WriterCSV;
import com.university.entity.classroom.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestWriterCSV {

    private static final String OUTPUT_FILE = "src/main/resources/test_output.csv";
    private List<Student> students;

    @BeforeEach
    public void setUp() {
        // Crear estudiantes y cursos de prueba
        students = new ArrayList<>();

        Student alice = new Student("Alice Azure");
        Course math = new Course("Mathematics");
        Course science = new Course("Science");
        alice.addSubject(math);
        alice.addSubject(science);
        students.add(alice);

        Student bob = new Student("Bob Brown");
        bob.addSubject(math); // Bob solo está en un curso
        students.add(bob);

        Student charlie = new Student("Charlie Cyan");
        Course history = new Course("History");
        charlie.addSubject(history);
        charlie.addSubject(science);
        students.add(charlie);
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Eliminar el archivo generado después de cada prueba
        Files.deleteIfExists(Paths.get(OUTPUT_FILE));
    }

    @Test
    public void testWriteAggregatedData() throws IOException {
        // Ejecutar el método para escribir el archivo
        WriterCSV.writeAggregatedData(OUTPUT_FILE, students);

        // Leer el archivo generado y verificar su contenido
        try (BufferedReader reader = new BufferedReader(new FileReader(OUTPUT_FILE))) {
            String header = reader.readLine();
            assertEquals("Student_Name,Course_Count", header);

            String line = reader.readLine();
            assertEquals("Alice Azure,2", line); // Alice está en 2 cursos

            line = reader.readLine();
            assertEquals("Bob Brown,1", line); // Bob está en 1 curso

            line = reader.readLine();
            assertEquals("Charlie Cyan,2", line); // Charlie está en 2 cursos

            assertTrue(reader.readLine() == null); // Verificar que no hay más líneas
        }
    }

    @Test
    public void testAlphabeticalOrder() throws IOException {
        // Generar el archivo de salida
        WriterCSV.writeAggregatedData(OUTPUT_FILE, students);

        // Leer el archivo y verificar que los estudiantes están en orden alfabético
        try (BufferedReader reader = new BufferedReader(new FileReader(OUTPUT_FILE))) {
            reader.readLine(); // Omitir el encabezado

            String firstLine = reader.readLine();
            assertEquals("Alice Azure,2", firstLine);

            String secondLine = reader.readLine();
            assertEquals("Bob Brown,1", secondLine);

            String thirdLine = reader.readLine();
            assertEquals("Charlie Cyan,2", thirdLine);
        }
    }
}

