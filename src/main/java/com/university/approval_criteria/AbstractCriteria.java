package com.university.approval_criteria;

import com.university.csv.part_3.ToStrCSV_3;
import com.university.evaluation.Evaluation;
import com.university.student.Student;

import java.util.List;

public abstract class AbstractCriteria implements ToStrCSV_3<AbstractCriteria>{
    protected List<Evaluation> evaluationList;
    protected Float grade;
    protected Boolean approved;
    protected String criteriaType;
    protected Student student;


    public AbstractCriteria(String criteriaType,List<Evaluation> evaluations, Float grade) {
        this.evaluationList = evaluations;
        this.grade = grade;
        this.approved = false;
        this.criteriaType = criteriaType;
        this.student = null;
    }
    protected abstract boolean checkIfIsApproved();
    public boolean isApproved(){
        approved = checkIfIsApproved();
        return approved;
    }

    @Override
    public String toStrCSV_3() {
        if (approved == false) {
            return this.getEvaluationSubject() + ",Fail," + criteriaType.toLowerCase() +":  "+grade.toString() +"," +evaluationList.toString().replace("[","").replace("]","");
        } else {
            return this.getEvaluationSubject() + ",PASS," + criteriaType.toLowerCase() +":  "+grade.toString() +"," +evaluationList.toString().replace("[","").replace("]","");
        }
    }

    public List<Evaluation> listEvaluations() {

        return evaluationList;
    }
    public String getEvaluationSubject(){

        return listEvaluations().getFirst().getCourse().getSubject();
    }

    public String getCriteriaType() {
        return criteriaType;
    }
    public Student getStudent() {

        return student;
    }
    public void setStudent(Student student) {

        this.student = student;
    }
}
