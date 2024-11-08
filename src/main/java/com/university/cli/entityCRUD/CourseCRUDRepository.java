package com.university.cli.entityCRUD;

import com.university.cli.CRUDRepository;
import com.university.entity.classroom.Course;


import java.util.ArrayList;

public class CourseCRUDRepository implements CRUDRepository<Course> {
    ArrayList<Course> courseRepository = new ArrayList<>();

    @Override
    public void create(Course entity) {
        if (!courseRepository.contains(entity)){

            courseRepository.add(entity);

            System.out.println("\nThe course, " +
                    entity.getSubject() +
                    ", was created with Id number " + entity.getId());
        }else {
            System.out.println("\nCourse already exists!");
        } 
    }

    @Override
    public Course read(int id) {
        for (Course course : courseRepository){
            if (course.getId() == id){
                return course;

            }
        } System.out.println("\nInvalid ID. The Course with id "+id+" does not exist");
         
        return  null;
    }

    @Override
    public void update(int id, Course entity) {
        Course outdatedEntity = this.read(id);
        if (outdatedEntity != null) { String outdatedEntitySubject = outdatedEntity.getSubject();
            outdatedEntity.setSubject(entity.getSubject());
            System.out.println("\nCourse, "+
                    outdatedEntitySubject+", was updated to :" +
                    entity.getSubject());
        } else {
            System.out.println("\nInvalid ID. The Course with id "+id+" does not exist");
        }
         
    }

    @Override
    public void delete(int id) {
        Course entityDelete = this.read(id);
        if (entityDelete != null){
            courseRepository.remove(entityDelete);
            System.out.println("\nThe Course, "+ entityDelete.getSubject()+",was deleted");
        }else {
            System.out.println("\nInvalid ID. The student with id" +id+ "does not exist");
        }
         
    }

    @Override
    public String getIdentifier() {
        return "Course";
    }

    @Override
    public Class<Course> getEntityClass() {
        return Course.class;
    }

    public void sleep_twoSec_method(){
        try{
            Thread.sleep(2000);
        } catch (Exception e){
            System.out.println("\nSomething went wrong. Sleep error");
        }
    }
}
