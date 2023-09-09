package com.school.model;

import java.io.Serializable;

public class SubjectModel implements Serializable{

    private int id_subject;
    private String subject_name;
    private int id_grade_group;
    private String grade;
    private String grad_group;
    private String to_desc_gg;

    public SubjectModel() {
    }

    public SubjectModel(int id_subject, String subject_name, int id_grade_group, String grade, String grad_group) {
        this.id_subject = id_subject;
        this.subject_name = subject_name;
        this.id_grade_group = id_grade_group;
        this.grade = grade;
        this.grad_group = grad_group;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
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

    public int getId_grade_group() {
        return id_grade_group;
    }

    public void setId_grade_group(int id_grade_group) {
        this.id_grade_group = id_grade_group;
    }

    public String getTo_desc_gg() {
        return grade + " " + grad_group;
    }
    
    @Override
    public String toString() {
        return "SubjectModel{" + "id_subject=" + id_subject + ", subject_name=" + subject_name + ", id_grade_group=" + id_grade_group + ", grade=" + grade + ", grad_group=" + grad_group + '}';
    }

}
