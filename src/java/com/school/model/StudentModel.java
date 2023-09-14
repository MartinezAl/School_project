package com.school.model;

import java.io.Serializable;

public class StudentModel implements Serializable {

    private int id_student;
    private int id_tutor;
    private String complete_name;
    private String gender;
    private String curp;
    private String birthdate;
    private String address;
    private String phone_number;

    public StudentModel() {
    }

    public StudentModel(int id_student, int id_tutor, String complete_name, String gender, String curp, String birthdate, String address, String phone_number) {
        this.id_student = id_student;
        this.id_tutor = id_tutor;
        this.complete_name = complete_name;
        this.gender = gender;
        this.curp = curp;
        this.birthdate = birthdate;
        this.address = address;
        this.phone_number = phone_number;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public int getId_tutor() {
        return id_tutor;
    }

    public void setId_tutor(int id_tutor) {
        this.id_tutor = id_tutor;
    }

    public String getComplete_name() {
        return complete_name;
    }

    public void setComplete_name(String complete_name) {
        this.complete_name = complete_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
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

    @Override
    public String toString() {
        return "StudentModel{" + "id_student=" + id_student + ", id_tutor=" + id_tutor + ", complete_name=" + complete_name + ", gender=" + gender + ", curp=" + curp + ", birthdate=" + birthdate + ", address=" + address + ", phone_number=" + phone_number + '}';
    }
}
