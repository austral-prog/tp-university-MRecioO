package com.university.csv.part_3;

import com.university.approval_criteria.AbstractCriteria;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WriterCSV3_RAW {

    public static void writeApprovalResults(String filePath, Map<String, AbstractCriteria> criteriaMap) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Escribir el encabezado del archivo CSV
            writer.write("Student_Name,Subject,Approval_Status,criteria,1,2,3,4,5,6,7,8,9,10\n");

            // Ordenar los criterios por nombre del estudiante y luego por materia
            List<AbstractCriteria> sortedCriteriaList = criteriaMap.values().stream()
                    .sorted((c1, c2) -> {
                        int studentComparison = c1.getStudent().getName().compareTo(c2.getStudent().getName());
                        if (studentComparison != 0) {
                            return studentComparison;
                        }
                        return c1.getEvaluationSubject().compareTo(c2.getEvaluationSubject());
                    }).toList();

            // Iterar sobre cada criterio y escribir el resultado en el archivo
            for (AbstractCriteria criteria : sortedCriteriaList) {
                // Verificar si el estudiante está aprobado según el criterio
                criteria.isApproved();



                writer.write(criteria.getStudent().toStrCSV_3() + criteria.toStrCSV_3() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}