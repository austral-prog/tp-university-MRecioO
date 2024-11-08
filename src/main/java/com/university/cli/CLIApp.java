package com.university.cli;

import com.university.cli.entityCRUD.*;
import com.university.entity.classroom.*;
import com.university.entity.evaluation.Evaluation;

import java.util.List;

public class CLIApp {
    public static void main(String[] args) {
        CLI cli = new CLIHelper();
        CRUDRepository<Student> studentRepository = new StudentCRUDRepository();
        CRUDRepository<Course> subjectRepository = new CourseCRUDRepository();
        CRUDRepository<Evaluation> evaluationRepository = new EvaluationCRUDRepository();

        // Creamos la lista de repositorios
        List<CRUDRepository<?>> repositoryList = List.of(studentRepository, subjectRepository, evaluationRepository);

        // Convertimos la lista a un array de CRUDRepository<?>
        CRUDRepository<?>[] repositories = repositoryList.toArray(new CRUDRepository<?>[0]);

        // Ejecuta la CLI con el array de repositorios
        cli.runCLI(repositories);
    }
}
