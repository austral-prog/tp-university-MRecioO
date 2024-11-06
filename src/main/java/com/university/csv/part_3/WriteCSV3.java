package com.university.csv.part_3;

import com.university.approval_criteria.AbstractCriteria;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WriteCSV3 {

    public static void writeSubjectApprovalReport(String filePath, Map<String, List<AbstractCriteria>> criteriaListMap) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Escribir el encabezado del archivo CSV
            writer.write("Student_Name,Subject,Approval_Status\n");

            // Ordenar criterios por nombre de estudiante y luego por materia
            List<Map.Entry<String, List<AbstractCriteria>>> sortedCriteriaList = criteriaListMap.entrySet().stream()
                    .sorted((entry1, entry2) -> {
                        String[] keyParts1 = entry1.getKey().split("_");
                        String[] keyParts2 = entry2.getKey().split("_");

                        // Comparar por nombre del estudiante
                        int studentComparison = keyParts1[1].compareTo(keyParts2[1]);
                        if (studentComparison != 0) {
                            return studentComparison;
                        }

                        // Comparar por materia
                        return keyParts1[0].compareTo(keyParts2[0]);
                    })
                    .collect(Collectors.toList());

            // Iterar sobre cada entrada ordenada y escribir el resultado en el archivo
            for (Map.Entry<String, List<AbstractCriteria>> entry : sortedCriteriaList) {
                String[] keyParts = entry.getKey().split("_");
                String subject = keyParts[0];
                String studentName = keyParts[1];
                List<AbstractCriteria> criteriaList = entry.getValue();

                // Verificar si el estudiante aprueba la materia (si aprueba todos los criterios)
                boolean passedAllCriteria = criteriaList.stream().allMatch(AbstractCriteria::isApproved);
                String approvalStatus = passedAllCriteria ? "PASS" : "FAIL";

                // Escribir en el archivo CSV
                writer.write(studentName + "," + subject + "," + approvalStatus + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
