package com.university.csv.part_3;

import com.university.approval_criteria.AbstractCriteria;
import com.university.course.Course;
import com.university.evaluation.Evaluation;
import com.university.evaluation.Exercise;
import com.university.student.Student;


import java.util.List;
import java.util.Map;

public class CSVResult3 {

    private Map<String, AbstractCriteria> criteriaMap;
    private Map<String, List<AbstractCriteria> >criteriaListMap;

    public CSVResult3(Map<String, AbstractCriteria> criteriaMap, Map<String, List<AbstractCriteria>> criteriaListMap) {
        this.criteriaMap = criteriaMap;
        this.criteriaListMap = criteriaListMap;
    }
    public Map<String, AbstractCriteria> getCriteriaMap() {
        return criteriaMap;
    }
    public Map<String, List<AbstractCriteria>> getCriteriaListMap() {
        return criteriaListMap;
    }
}
