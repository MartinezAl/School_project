package com.school.model;

import java.io.Serializable;
import java.util.List;

public class ParamsStudentModel implements Serializable {
    
    private List<String> list_grade_group;
    private String name_student;
    private String tuition_number;
    private String curp;

    public ParamsStudentModel() {
    }

    public ParamsStudentModel(List<String> list_grade_group, String name_student, String tuition_number, String curp) {
        this.list_grade_group = list_grade_group;
        this.name_student = name_student;
        this.tuition_number = tuition_number;
        this.curp = curp;
    }

    public List<String> getList_grade_group() {
        return list_grade_group;
    }

    public void setList_grade_group(List<String> list_grade_group) {
        this.list_grade_group = list_grade_group;
    }

    public String getName_student() {
        return name_student;
    }

    public void setName_student(String name_student) {
        this.name_student = name_student;
    }

    public String getTuition_number() {
        return tuition_number;
    }

    public void setTuition_number(String tuition_number) {
        this.tuition_number = tuition_number;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    @Override
    public String toString() {
        return "ParamsStudentModel{" + "list_grade_group=" + list_grade_group + ", name_student=" + name_student + ", tuition_number=" + tuition_number + ", curp=" + curp + '}';
    }
}
