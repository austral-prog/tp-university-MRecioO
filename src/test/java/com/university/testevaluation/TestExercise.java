package com.university.testevaluation;

import com.university.evaluation.Exercise;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestExercise {

    @Test
    void testExerciseCreation() {
        Exercise exercise = new Exercise("TP Final", 4);
        assertEquals("TP Final", exercise.getName());
        assertEquals(4, exercise.getGrade());

    }
}

