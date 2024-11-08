package com.university.cli.entityCRUD;

import com.university.cli.CRUDRepository;
import com.university.entity.classroom.Course;
import com.university.entity.classroom.Student;
import com.university.entity.evaluation.Evaluation;

import java.util.ArrayList;

public class EvaluationCRUDRepository implements CRUDRepository<Evaluation> {
    ArrayList<Evaluation> evaluationRepository = new ArrayList<>();

    @Override
    public void create(Evaluation entity) {
        if (!evaluationRepository.contains(entity)) {
            evaluationRepository.add(entity);
            Course course = entity.getCourse();
            Student student = entity.getStudent();
            course.addEvaluation(entity);
            student.addEvaluation(entity);
            System.out.println("\nThe Evaluation, " +
                    entity.getEvaluationName() + " whit type " + entity.getEvaluationType().toLowerCase() +
                    ", was created with Id number " + entity.getId() +
                    ", and added to "+course+" and "+student);

        } else {
            System.out.println("\nThe Evaluation already exists!");
        }
         
    }

    @Override
    public Evaluation read(int id) {
        for (Evaluation evaluation : evaluationRepository) {
            if (evaluation.getId() == id) {
                return evaluation;
            }
        } System.out.println("\nInvalid ID. The Evaluation with id "+id+" does not exist");
         
        return null;
    }

    @Override
    public void update(int id, Evaluation entity) {
        Evaluation outdatedEntity = this.read(id);

        if (outdatedEntity != null) { Student oldStudent = outdatedEntity.getStudent();
            Course oldCourse = outdatedEntity.getCourse();


            outdatedEntity.setEvaluationName(entity.getEvaluationName());
            outdatedEntity.setEvaluationType(entity.getEvaluationType());

            if(!oldStudent.equals(entity.getStudent())) {
                outdatedEntity.setStudent(entity.getStudent());
                oldStudent.removeEvaluation(outdatedEntity);
                entity.getStudent().addEvaluation(entity);
            }
            if(!oldCourse.equals(outdatedEntity.getCourse())) {
                outdatedEntity.setCourse(entity.getCourse());
                oldCourse.removeEvaluation(outdatedEntity);
                entity.getCourse().addEvaluation(entity);
            }

            System.out.println("\nThe Evaluation was updated to: "  +
                                        entity.getEvaluationName() + " whit type " + entity.getEvaluationType().toLowerCase() +
                                        ", was reassigned to  " + entity.getStudent()  + " and "+ entity.getCourse());
        }else {
            System.out.println("Invalid ID. The Course with id "+id+" does not exist");
        }
    }

    @Override
    public void delete(int id) {
        Evaluation entityDelete = this.read(id);
        if (entityDelete != null){
            Course course = entityDelete.getCourse();
            Student student = entityDelete.getStudent();
            course.removeEvaluation(entityDelete);
            student.removeEvaluation(entityDelete);
            evaluationRepository.remove(entityDelete);
            System.out.println("\nThe Evaluation, "+ entityDelete.getEvaluationName()+", was deleted and removed from the Student and Course:"+student+" and "+course);
        }else {
            System.out.println("\nInvalid ID. The Evaluation with id" +id+ "does not exist");
        }
         
    }

    @Override
    public String getIdentifier() {
        return "Evaluation";
    }

    @Override
    public Class<Evaluation> getEntityClass() {
        return Evaluation.class;
    }
    public void sleep_twoSec_method(){
        try{
            Thread.sleep(2000);
        } catch (Exception e){
            System.out.println("\nSomething went wrong. Sleep error");
        }
    }
}
