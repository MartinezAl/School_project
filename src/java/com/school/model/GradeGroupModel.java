package com.school.model;

import java.io.Serializable;

public class GradeGroupModel implements Serializable {

    private int id_grade_group;
    private String grade;
    private String grad_group;
    private String to_desc;

    public GradeGroupModel() {
    }

    public GradeGroupModel(int id_grade_group, String grade, String grad_group) {
        this.id_grade_group = id_grade_group;
        this.grade = grade;
        this.grad_group = grad_group;
    }

    public GradeGroupModel(String grade, String grad_group) {
        this.grade = grade;
        this.grad_group = grad_group;
    }

    public int getId_grade_group() {
        return id_grade_group;
    }

    public void setId_grade_group(int id_grade_group) {
        this.id_grade_group = id_grade_group;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrad_group() {
        return grad_group;
    }

    public void setGrad_group(String grad_group) {
        this.grad_group = grad_group;
    }

    public String getTo_desc() {
        return grade + " " + grad_group;
    }

    @Override
    public String toString() {
        return "GradeGroupModel{" + "id_grade_group=" + id_grade_group + ", grade=" + grade + ", grad_group=" + grad_group + '}';
    }
}
