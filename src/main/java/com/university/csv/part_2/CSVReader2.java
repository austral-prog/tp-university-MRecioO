package com.university.csv.part_2;

import com.university.evaluation.Evaluation;
import com.university.evaluation.types.EvaluationFactory;
import com.university.evaluation.Exercise;
import com.university.course.Course;
import com.university.student.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVReader2 {

    public static CSVResult2 read2(String filePath, Map<String, Student> studentMap, Map<String, Course> courseMap) {

        Map<String, Evaluation> evaluationMap = new HashMap<>();
        Map<Evaluation, List<Exercise>> evalExerciseMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Leer el encabezado y descartarlo

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Suponiendo que el formato es: Student,Subject,Evaluation_Type,Evaluation_Name,Exercise_Name,Grade
                String studentName = values[0].trim();
                String subject = values[1].trim();
                String evaluationType = values[2].trim();
                String evaluationName = values[3].trim();
                String exerciseName = values[4].trim();
                float Grade = Float.parseFloat(values[5].trim());

                // Obtiene o crear el estudiante
                Student student = studentMap.getOrDefault(studentName, new Student(studentName));
                Student existentStudent = studentMap.putIfAbsent(studentName, student);

                // Obtiene o crear el curso
                Course course = courseMap.getOrDefault(subject, new Course(subject));
                Course existentCourse = courseMap.putIfAbsent(subject, course);
                // Si el estudiante y/o el curso no estaba en el input_1
                if (existentStudent == null || existentCourse == null) {
                    student.addSubject(course);
                    course.addStudent(student);
                }
                // Crea clave para encontrar una evaluacion sin tener en cuenta ejercicios
                String keyEva = studentName + subject + evaluationType + evaluationName;
                Evaluation evaluation = evaluationMap.getOrDefault(keyEva, EvaluationFactory.createEvaluation(student, course, evaluationType, evaluationName));
                Evaluation existentEvaluation = evaluationMap.putIfAbsent(keyEva, evaluation);

                // Crea y añadir ejercicio a la evaluación
                Exercise exercise = new Exercise(exerciseName, Grade);
                evaluation.addExercise(exercise);
                // Si la evaluacion no existia, se agrega al estudiante y al curso
                if (existentEvaluation == null) {
                    student.addEvaluation(evaluation);
                    course.addEvaluation(evaluation);
                }
                // Añadir el ejercicio al mapa de evaluaciones con listas de ejercicos
                evalExerciseMap.computeIfAbsent(evaluation, k -> new ArrayList<>());
                if (!evalExerciseMap.get(evaluation).contains(exercise)) {
                    evalExerciseMap.get(evaluation).add(exercise);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new CSVResult2(studentMap, courseMap, evaluationMap, evalExerciseMap);
    }
}