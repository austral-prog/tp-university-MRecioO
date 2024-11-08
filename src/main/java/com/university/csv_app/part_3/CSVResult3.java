package com.university.csv_app.part_3;

import com.university.entity.evaluation.approval_criteria.AbstractCriteria;


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
