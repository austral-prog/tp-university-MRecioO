package com.university.testevaluation;

import com.university.entity.classroom.Course;
import com.university.entity.evaluation.Exercise;
import com.university.entity.evaluation.types.FinalExam;
import com.university.entity.evaluation.types.OralExam;
import com.university.entity.evaluation.types.PracticalWork;
import com.university.entity.evaluation.types.WrittenExam;
import com.university.entity.classroom.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEvaluation {

    private Student student;
    private Course course;
    private FinalExam finalExam;
    private PracticalWork practicalWork;
    private OralExam oralExam;
    private WrittenExam writtenExam;

    @BeforeEach
    void setUp() {
        student = new Student("Alice Azure");
        course = new Course("Computer Science");

        finalExam = new FinalExam(student, course, "FINAL_PRACTICAL_WORK", "TP Final");//Inputs: Alice Azure,Computer Science,FINAL_PRACTICAL_WORK,TP Final, (Exercise)    Output: Computer Science,TP Final,Alice Azure,28.0
        practicalWork = new PracticalWork(student, course, "PRACTICAL_WORK", "TP1");//Inputs: Charlie Beige,Chemistry,PRACTICAL_WORK,TP1, (Exercise)    Output: Chemistry,TP1,Charlie Beige,3.0
        oralExam = new OralExam(student, course, "ORAL_EXAM", "Primer Parcial");//Inputs: Alice Azure,Sociology,ORAL_EXAM,Primer Parcial,Ej1,9   Output: Sociology,Primer Parcial,Alice Azure,9.0
        writtenExam = new WrittenExam(student, course, "WRITTEN_EXAM", "Examen Final");//Inputs: Alice Azure,Biology,WRITTEN_EXAM,Examen Final, (Exersises)   Output: Biology,Examen Final,Alice Azure,4.6
    }

    @Test
    void testFinalExamCalculateGrade() {
        finalExam.addExercise(new Exercise("Ej4", 6));
        finalExam.addExercise(new Exercise("Ej2", 8));
        finalExam.addExercise(new Exercise("Ej1", 7));
        finalExam.addExercise(new Exercise("Ej5", 1));
        finalExam.addExercise(new Exercise("Ej3", 6));
        assertEquals(28, finalExam.calculateGrade(),"La suma");
    }

    @Test
    void testPracticalWorkCalculateGrade() {//Inputs: Charlie Beige,Chemistry,PRACTICAL_WORK,TP1, Exercise    Output: Chemistry,TP1,Charlie Beige,3.0
        practicalWork.addExercise(new Exercise("Ej3", 0));
        practicalWork.addExercise(new Exercise("Ej1", 5));
        practicalWork.addExercise(new Exercise("Ej2", 3));
        assertEquals(3, practicalWork.calculateGrade(),"El ultimo");
    }

    @Test
    void testOralExamCalculateGrade() {//Inputs: Alice Azure,Sociology,ORAL_EXAM,Primer Parcial,Ej1,9   Output: Sociology,Primer Parcial,Alice Azure,9.0
        oralExam.addExercise(new Exercise("Ej1", 9));
        assertEquals(9, oralExam.calculateGrade(),"El unico");
    }

    @Test
    void testWrittenExamCalculateGrade() {//Inputs: Alice Azure,Biology,WRITTEN_EXAM,Examen Final, (Exersises)   Output: Biology,Examen Final,Alice Azure,4.6
        writtenExam.addExercise(new Exercise("Ej6", 6));
        writtenExam.addExercise(new Exercise("Ej1", 6));
        writtenExam.addExercise(new Exercise("Ej4", 10));
        writtenExam.addExercise(new Exercise("Ej7", 2));
        writtenExam.addExercise(new Exercise("Ej8", 2));
        writtenExam.addExercise(new Exercise("Ej10", 1));
        writtenExam.addExercise(new Exercise("Ej5", 3));
        writtenExam.addExercise(new Exercise("Ej3", 5));
        writtenExam.addExercise(new Exercise("Ej2", 3));
        writtenExam.addExercise(new Exercise("Ej9", 8));

        assertTrue(writtenExam.listExercises().size() == 10);
        assertEquals(4.6, writtenExam.calculateGrade(),"El promedio");
    }
    @Test
    void testEquals(){
        FinalExam copia = new FinalExam(student, course, "FINAL_PRACTICAL_WORK", "TP Final");
        FinalExam diffStudent = new FinalExam(new Student("Mati"), course, "FINAL_PRACTICAL_WORK", "TP Final");
        FinalExam diffCourse = new FinalExam(student, new Course("Prog"), "FINAL_PRACTICAL_WORK", "TP Final");
        FinalExam diffEvalType = new FinalExam(student, course, "no puede paser en teoria", "TP Final");
        FinalExam diffEvalNAme = new FinalExam(student, course, "FINAL_PRACTICAL_WORK", "NO TP FINAL");
        PracticalWork diiffClass = new PracticalWork(student, course, "FINAL_PRACTICAL_WORK", "TP Final");

        assertTrue(finalExam.equals(finalExam));
        assertTrue(finalExam.equals(copia));
        assertFalse(finalExam.equals(diffStudent));
        assertFalse(finalExam.equals(diffCourse));
        assertFalse(finalExam.equals(diffEvalType));
        assertFalse(finalExam.equals(diffEvalNAme));
        assertFalse(finalExam.equals(diiffClass));
        assertFalse(finalExam.equals(null));

        finalExam.addExercise(new Exercise("Ej1", 6));
        assertTrue(finalExam.equals(copia),"no impoorta si agrego un ejercicio");

    }
}
