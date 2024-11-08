package com.university.cli.entityCRUD;

import com.university.cli.CRUDRepository;
import com.university.entity.classroom.Student;

import java.util.ArrayList;

public class StudentCRUDRepository implements CRUDRepository<Student> {
    ArrayList<Student> studentRepository = new ArrayList<>();

    @Override
    public void create(Student entity) {
        if (!studentRepository.contains(entity)) {
            studentRepository.add(entity);
            System.out.println("\nThe student, " +
                    entity.getName() +
                    ", was created with Id number " + entity.getId());

        } else {
            System.out.println("\nThe student already exists!");
        }
          
    }

    @Override
    public Student read(int id) {
        for (Student student : studentRepository) {
            if (student.getId() == id) {
                return student;
            }
        }System.out.println("\nInvalid ID. The student with id "+id+" does not exist");
          
        return null;
    }

    @Override
    public void update(int id, Student entity) {
        Student outdatedEntity = this.read(id);

        if (outdatedEntity != null) { String outdatedEntityName = outdatedEntity.getName();
            outdatedEntity.setName(entity.getName());
            System.out.println("\nStudent, "+
                    outdatedEntityName+", was updated to :" +
                    entity.getName());
        } else {
            System.out.println("\nInvalid ID. The student with id "+id+" does not exist");
        }
          
    }

    @Override
    public void delete(int id) {
        Student entityDelete = this.read(id);
        if (entityDelete != null){
            studentRepository.remove(entityDelete);
            System.out.println("\nThe student, "+ entityDelete.getName()+",was deleted");
        }else {
            System.out.println("\nInvalid ID. The student with id" +id+ "does not exist");
        }
          
    }

    @Override
    public String getIdentifier() {
        return "Student";
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    public void sleep_twoSec_method(){
        try{
            Thread.sleep(2000);
        } catch (Exception e){
            System.out.println("\nSomething went wrong. Sleep error");
        }
    }
}
