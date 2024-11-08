package com.university.csv_app.part_1;

import com.university.entity.classroom.Course;
import com.university.entity.classroom.Student;

import java.util.List;
import java.util.Map;

public class CSVResult {
    private Map<String, Student> studentMap;
    private Map<String, Course> courseMap;
    private Map<Student, List<Course>> studentCourseMap;

    public CSVResult(Map<String, Student> studentMap, Map<String, Course> courseMap, Map<Student, List<Course>> studentCourseMap) {
        this.studentMap = studentMap;
        this.courseMap = courseMap;
        this.studentCourseMap = studentCourseMap;
    }

    public Map<String, Student> getStudentMap() {
        return studentMap;
    }

    public Map<String, Course> getCourseMap() {
        return courseMap;
    }

    public Map<Student, List<Course>> getStudentCourseMap() {
        return studentCourseMap;
    }
}
