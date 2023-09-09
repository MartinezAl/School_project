package com.school.model;

import java.io.Serializable;

public class TeacherModel implements Serializable {

    private int id_teacher;
    private String complete_name;
    private String address;
    private String phone_number;
    private String email;
    private String curp;
    private String rfc;

    public TeacherModel() {
    }

    public TeacherModel(int id_teacher, String complete_name, String address, String phone_number, String email, String curp, String rfc) {
        this.id_teacher = id_teacher;
        this.complete_name = complete_name;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
        this.curp = curp;
        this.rfc = rfc;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    @Override
    public String toString() {
        return "TeacherModel{" + "id_teacher=" + id_teacher + ", complete_name=" + complete_name + ", address=" + address + ", phone_number=" + phone_number + ", email=" + email + ", curp=" + curp + ", rfc=" + rfc + '}';
    }
}
