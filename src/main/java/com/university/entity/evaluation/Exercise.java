package com.university.entity.evaluation;

import com.university.cli.entityCRUD.Entity;

public class Exercise implements Entity {
    private String name;
    private float grade;
    private int id;
    private static int idCounter = 0;

    public Exercise(String name, float grade) {
        this.name = name;
        this.grade = grade;
        this.id = ++idCounter;
    }

    public String getName() {
        return name;
    }

    public float getGrade() {
        return grade;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
