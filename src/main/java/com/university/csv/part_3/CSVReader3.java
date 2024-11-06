package com.university.csv.part_3;

import com.university.approval_criteria.AbstractCriteria;
import com.university.approval_criteria.types.CriteriaFactory;
import com.university.evaluation.Evaluation;
import com.university.evaluation.Exercise;
import com.university.student.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVReader3 {
    public static CSVResult3 readCriteria(String filePath, Map<Evaluation, List<Exercise>> evalExerciseMap, Map<String, Student> studentMap) {
        Map<String, AbstractCriteria> criteriaMap = new HashMap<>();
        Map<String, List<AbstractCriteria> >criteriaListMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Saltar encabezado

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String subject = values[0];
                String criteriaType = values[1];
                Float criteriaValue = Float.parseFloat(values[2]);

                // Iteramos sobre los estudiantes para cada materia especificada
                for (Student student : studentMap.values()) {
                    List<Evaluation> relevantEvaluations = new ArrayList<>();

                    // Buscar evaluaciones del estudiante en la materia especificada, usando el evalExerciseMap
                    for (Map.Entry<Evaluation, List<Exercise>> entry : evalExerciseMap.entrySet()) {
                        Evaluation evaluation = entry.getKey();

                        // Continua leyendo el CSV hasta terminar
                        for (int i = 3; i < values.length; i++) {
                            String evaluationName = values[i].trim();

                            // Filtrar evaluaciones por estudiante, materia y tipo de evaluación
                            if (evaluation.getStudent().equals(student) &&
                                    evaluation.getCourse().getSubject().equals(subject)) {
                                Evaluation matchEvaluation = student.findEvaluationsByNameAndCourse(evaluationName, evaluation.getCourse());
                                if (matchEvaluation != null) {
                                    if(!relevantEvaluations.contains(matchEvaluation)) {
                                        relevantEvaluations.add(matchEvaluation);
                                    }
                                }
                            }
                        }
                    }

                    if (!relevantEvaluations.isEmpty()) {

                        // Crear el criterio de aprobación utilizando las evaluaciones relevantes
                        AbstractCriteria criteria = CriteriaFactory.createCriteria(criteriaType, relevantEvaluations, criteriaValue);
                        criteria.setStudent(student);

                        // Clave única por estudiante y materia y criterio
                        // Para luego calcular si el estudiante pasa o no un unico criterio de una matera
                        String criteriaMapKey = subject + "_" + student.getName() + "_" + criteriaType;
                        criteriaMap.put(criteriaMapKey, criteria);

                        // Clave única por estudiante y materia
                        // Para luego calcular si el estudiant pasa o no la materia entera
                        String criteriaListMapKey = subject + "_" + student.getName();
                        criteriaListMap.computeIfAbsent(criteriaListMapKey, value -> new ArrayList<>());
                        if (!criteriaListMap.get(criteriaListMapKey).contains(criteria)) {
                            criteriaListMap.get(criteriaListMapKey).add(criteria);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CSVResult3(criteriaMap,criteriaListMap);
    }
}
