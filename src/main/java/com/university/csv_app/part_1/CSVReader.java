package com.university.csv_app.part_1;

import com.university.entity.classroom.Course;
import com.university.entity.classroom.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {

    public static CSVResult readFromCSV(String filePath) {
        Map<String, Student> studentMap = new HashMap<>();
        Map<String, Course> courseMap = new HashMap<>();
        Map<Student, List<Course>> mapSC = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Leer encabezado y descartarlo

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Suponiendo que el formato es: classroom,subject,studentName,studentEmail,teacher
                String classroom = values[0].trim();
                String subject = values[1].trim();
                String studentName = values[2].trim();
                String studentEmail = values[3].trim();
                String teacher = values[4].trim();

                // Manejo del estudiante
                Student student = studentMap.getOrDefault(studentName, new Student(studentName));
                if (!student.listEmail().contains(studentEmail)) {
                    student.addEmail(studentEmail);
                }
                studentMap.putIfAbsent(studentName, student);

                // Manejo del curso
                Course course = courseMap.getOrDefault(subject, new Course(subject));
                if (!course.listClassroom().contains(classroom)) {
                    course.addClassroom(classroom);
                }
                if (!course.listTeacher().contains(teacher)) {
                    course.addTeacher(teacher);
                }
                courseMap.putIfAbsent(subject, course);

                // Relación entre estudiante y curso
                mapSC.computeIfAbsent(student, k -> new ArrayList<>());
                if (!mapSC.get(student).contains(course)) {
                    mapSC.get(student).add(course);
                    student.addSubject(course);
                    course.addStudent(student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new CSVResult(studentMap, courseMap, mapSC);
    }
}
