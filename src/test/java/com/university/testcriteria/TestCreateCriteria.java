package com.university.testcriteria;

import com.university.approval_criteria.AbstractCriteria;
import com.university.approval_criteria.types.CriteriaFactory;
import com.university.approval_criteria.types.MaxAboveValue;
import com.university.approval_criteria.types.MinAboveValue;
import com.university.evaluation.Evaluation;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCreateCriteria {
    @Test
    public void testCreateCriteria() {
        List<Evaluation> evaluations = new ArrayList<>();
        String line ="Geography,AVERAGE_ABOVE_VALUE,6.0,TP1,TP4,TP2,TP3";
        String[] values = line.split(",");

        String criteriaType = values[1];
        Float criteriaValue = Float.parseFloat(values[2]);

        AbstractCriteria c1 = CriteriaFactory.createCriteria(criteriaType,  evaluations, criteriaValue);
        assertTrue("AVERAGE_ABOVE_VALUE".equals(c1.getCriteriaType()));


        AbstractCriteria c2 = CriteriaFactory.createCriteria("MAX_ABOVE_VALUE",  evaluations, criteriaValue);
        AbstractCriteria c3 = CriteriaFactory.createCriteria("MIN_ABOVE_VALUE",  evaluations, criteriaValue);

        assertTrue(c2 instanceof MaxAboveValue);
        assertTrue(c3 instanceof MinAboveValue);
    }
}
