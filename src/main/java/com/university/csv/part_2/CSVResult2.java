package com.university.csv.part_2;

import com.university.course.Course;
import com.university.evaluation.Evaluation;
import com.university.evaluation.Exercise;
import com.university.student.Student;

import java.util.List;
import java.util.Map;

public class CSVResult2 {
    private Map<String, Student> studentMap;
    private Map<String, Course> courseMap;
    private Map<String, Evaluation> evaluationMap ;
    private Map<Evaluation, List<Exercise>> evalExerciseMap ;

    public CSVResult2(Map<String, Student> studentMap, Map<String, Course> courseMap, Map<String, Evaluation> evaluationMap, Map<Evaluation, List<Exercise>> evalExerciseMap){
        this.studentMap = studentMap;
        this.courseMap = courseMap;
        this.evaluationMap = evaluationMap;
        this.evalExerciseMap = evalExerciseMap;
    }
    public Map<String, Student> getStudentMap(){
        return studentMap;
    }
    public Map<String, Course> getCourseMap(){
        return courseMap;
    }
    public Map<String, Evaluation> getEvaluationMap(){
        return evaluationMap;
    }
    public Map<Evaluation, List<Exercise>> getEvalExerciseMap(){
        return evalExerciseMap;
    }
}
