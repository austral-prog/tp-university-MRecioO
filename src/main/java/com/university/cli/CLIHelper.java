package com.university.cli;

import com.university.cli.entityCRUD.*;
import com.university.entity.classroom.*;
import com.university.entity.evaluation.Evaluation;
import com.university.entity.evaluation.types.EvaluationFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CLIHelper implements CLI {
    CRUDRepository<Student> studentRepository = null;
    CRUDRepository<Course> courseRepository = null;
    CRUDRepository<Evaluation> evaluationRepository = null;

    @Override
    public void runCLI(CRUDRepository<?>[] crudInterfaces) {
        Scanner myScanner = new Scanner(System.in);
        boolean keepRunning = true;
        initRepositories(crudInterfaces);

        while (keepRunning) {
            System.out.println("\n\nPlease, select the entity to manage:");
            for (int i = 0; i < crudInterfaces.length; i++) {
                System.out.println((i + 1) + ". " + crudInterfaces[i].getIdentifier());
            }
            System.out.println((crudInterfaces.length + 1) + ". Exit");

            int choice = -1;
            boolean validInput = false;


            do {
                try {
                    System.out.print("Enter your choice: ");
                    choice = myScanner.nextInt();
                    myScanner.nextLine();

                    if (choice < 1 || choice > crudInterfaces.length + 1) {
                        System.out.println("Invalid option. Please enter a number between 1 and " + (crudInterfaces.length + 1) + ".");
                    } else {
                        validInput = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a whole number.");
                    myScanner.nextLine();
                }
            } while (!validInput);

            if (choice == crudInterfaces.length + 1) {
                keepRunning = false; // Exit
            } else {
                CRUDRepository<?> selectedCRUD = crudInterfaces[choice - 1];
                processCrudOperations(myScanner, selectedCRUD);
            }
        }
    }

    private void initRepositories(CRUDRepository<?>[] crudInterfaces) {
        for (CRUDRepository<?> crudRepository : crudInterfaces) {
            switch (crudRepository.getIdentifier()) {
                case "Student" -> studentRepository = (CRUDRepository<Student>) crudRepository;
                case "Course" -> courseRepository = (CRUDRepository<Course>) crudRepository;
                case "Evaluation" -> evaluationRepository = (CRUDRepository<Evaluation>) crudRepository;
            }
        }
        if (studentRepository == null || courseRepository == null || evaluationRepository == null) {
            throw new IllegalArgumentException("Repository array does not contain all necessary repositories");
        }
    }

    private <T extends Entity> void processCrudOperations(Scanner myScanner, CRUDRepository<T> crud) {
        System.out.println("\nChoose an operation for " + crud.getIdentifier() + ":");
        System.out.println("1. Create");
        System.out.println("2. Read");
        System.out.println("3. Update");
        System.out.println("4. Delete");

        int operation = -1;
        boolean validInput = false;

        do {
            try {
                System.out.print("Enter your choice: ");
                operation = myScanner.nextInt();
                myScanner.nextLine();  // Consume newline

                if (operation < 1 || operation > 4) {
                    System.out.println("Invalid operation. Please enter a number between 1 and 4.");
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a whole number.");
                myScanner.nextLine(); // Consume input inválido
            }
        } while (!validInput);

        switch (operation) {
            case 1 -> createEntity(myScanner, crud);
            case 2 -> readEntity(myScanner, crud);
            case 3 -> updateEntity(myScanner, crud);
            case 4 -> deleteEntity(myScanner, crud);
        }
    }

    private Student createStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        return new Student(name);
    }

    private Course createCourse(Scanner scanner) {
        System.out.print("Enter the course name: ");
        String subject = scanner.nextLine();
        return new Course(subject);
    }

    private Evaluation createEvaluation(Scanner scanner) {
        System.out.print("Enter student ID to be assessed: ");
        int idStudent = scanner.nextInt();
        System.out.print("Enter the course ID to be evaluated: ");
        int idCourse = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter one of these evaluation types: final_practical_work, practical_work, oral_exam, written_exam: ");
        String type = scanner.nextLine().toUpperCase();
        System.out.print("Enter evaluation name: ");
        String name = scanner.nextLine();

        Student student = studentRepository.read(idStudent);
        Course course = courseRepository.read(idCourse);

        if (student == null || course == null) {
            System.out.println("Invalid student or course ID. Please verify.");
            return null;
        }
        return EvaluationFactory.createEvaluation(student, course, type, name);
    }

    private <T extends Entity> void createEntity(Scanner scanner, CRUDRepository<T> crud) {
        T entity = switch (crud.getEntityClass().getSimpleName()) {
            case "Student" -> (T) createStudent(scanner);
            case "Course" -> (T) createCourse(scanner);
            case "Evaluation" -> {
                Evaluation evaluation = createEvaluation(scanner);
                if (evaluation == null) {
                    System.out.println("Creation of evaluation failed. Please check the input data.");
                    yield null;
                }
                yield (T) evaluation;
            }
            default -> throw new IllegalArgumentException("Unsupported entity type.");
        };
        if (entity != null) {
            crud.create(entity);
        }
    }

    private <T extends Entity> void readEntity(Scanner scanner, CRUDRepository<T> crud) {
        System.out.print("Enter the ID to read: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        T entity = crud.read(id);
        if (entity != null) {
            System.out.println("Entity found: " + entity);
        } else {
            System.out.println("Entity not found.");
        }
    }

    private Evaluation updateEvaluation(Scanner scanner) {
        System.out.print("Enter student ID to be assessed on the new evaluation: ");
        int idStudent = scanner.nextInt();
        System.out.print("Enter the course ID to be evaluated on the new evaluation: ");
        int idCourse = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the new evaluation type: final_practical_work, practical_work, oral_exam, written_exam: ");
        String type = scanner.nextLine().toUpperCase();
        System.out.print("Enter the new evaluation name: ");
        String name = scanner.nextLine();

        Student student = studentRepository.read(idStudent);
        Course course = courseRepository.read(idCourse);

        if (student == null || course == null) {
            System.out.println("Invalid student or course ID. Update failed.");
            return null;
        }
        return EvaluationFactory.createEvaluation(student, course, type, name);
    }

    private <T extends Entity> void updateEntity(Scanner scanner, CRUDRepository<T> crud) {
        System.out.print("Enter the ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        T updatedEntity = switch (crud.getEntityClass().getSimpleName()) {
            case "Student" -> (T) new Student(scanner.nextLine());
            case "Course" -> (T) new Course(scanner.nextLine());
            case "Evaluation" -> {
                Evaluation updatedEval = updateEvaluation(scanner);
                if (updatedEval == null) {
                    System.out.println("Update of evaluation failed. Please check the input data.");
                    yield null;
                }
                yield (T) updatedEval;
            }
            default -> throw new IllegalArgumentException("Unsupported entity type.");
        };

        if (updatedEntity != null) {
            crud.update(id, updatedEntity);
        }
    }

    private <T extends Entity> void deleteEntity(Scanner scanner, CRUDRepository<T> crud) {
        System.out.print("Enter the ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        crud.delete(id);
    }
}
