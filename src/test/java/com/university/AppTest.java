package com.university;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.university.csv_app.App;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AppTest {

    @Test
    public void testSolutionCSVMatchesExpected() {
        // Definir rutas de archivos para ambas partes
        String solutionFilePath1 = "src/main/resources/solution.csv";
        String expectedFilePath1 = "src/main/resources/expected.csv";
        String solutionFilePath2 = "src/main/resources/solution2.csv";
        String expectedFilePath2 = "src/main/resources/expected_2.csv";

        // Verificar que ninguno de los archivos de salida exista antes de la ejecución del test
        if (Files.exists(Paths.get(solutionFilePath1)) || Files.exists(Paths.get(solutionFilePath2))) {
            fail("Los archivos solution.csv o solution_2.csv existen antes de ejecutar el test.");
        }

        // Ejecutar el método main de la clase App
        try {
            App.main(new String[]{});  // Correr el main de la aplicación
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al ejecutar App.main()");
        }

        // Verificar que ambos archivos de salida se hayan creado después de la ejecución del main
        if (!Files.exists(Paths.get(solutionFilePath1)) || !Files.exists(Paths.get(solutionFilePath2))) {
            fail("Los archivos solution.csv o solution_2.csv no se crearon tras ejecutar el test.");
        }

        // Comparar solution.csv con expected.csv
        verificarArchivos(solutionFilePath1, expectedFilePath1);
        // Comparar solution_2.csv con expected_2.csv
        verificarArchivos(solutionFilePath2, expectedFilePath2);
    }

    private void verificarArchivos(String solutionFilePath, String expectedFilePath) {
        try (BufferedReader solutionReader = new BufferedReader(new FileReader(solutionFilePath));
             BufferedReader expectedReader = new BufferedReader(new FileReader(expectedFilePath))) {

            String solutionLine;
            String expectedLine;

            while ((solutionLine = solutionReader.readLine()) != null &&
                    (expectedLine = expectedReader.readLine()) != null) {
                assertEquals(expectedLine, solutionLine, "Discrepancia encontrada en el contenido del archivo CSV.");
            }

            // Confirmar que ambos archivos tienen el mismo número de líneas
            assertEquals(solutionReader.readLine(), expectedReader.readLine(), "Los archivos tienen un número diferente de líneas.");

        } catch (IOException e) {
            e.printStackTrace();
            fail("Error al comparar los archivos " + solutionFilePath + " y " + expectedFilePath);
        }
    }
}
