package com.school.model;

import java.io.Serializable;

public class GradeGroupTeacherModel implements Serializable {

    private int id_teacher_gra_gro;
    private int id_teacher;
    private String complete_name;
    private String email;
    private String phone_number;

    public GradeGroupTeacherModel() {
    }

    public GradeGroupTeacherModel(int id_teacher_gra_gro, int id_teacher, String complete_name, String email, String phone_number) {
        this.id_teacher_gra_gro = id_teacher_gra_gro;
        this.id_teacher = id_teacher;
        this.complete_name = complete_name;
        this.email = email;
        this.phone_number = phone_number;
    }

    public int getId_teacher_gra_gro() {
        return id_teacher_gra_gro;
    }

    public void setId_teacher_gra_gro(int id_teacher_gra_gro) {
        this.id_teacher_gra_gro = id_teacher_gra_gro;
    }

    public int getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
    }

    public String getComplete_name() {
        return complete_name;
    }

    public void setComplete_name(String complete_name) {
        this.complete_name = complete_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "GradeGroupTeacherModel{" + "id_teacher_gra_gro=" + id_teacher_gra_gro + ", id_teacher=" + id_teacher + ", complete_name=" + complete_name + ", email=" + email + ", phone_number=" + phone_number + '}';
    }
}
