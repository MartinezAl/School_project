package com.school.model;

import java.io.Serializable;

public class StudentModel implements Serializable {

    private int id_student;
    private int id_tutor;
    private String complete_name;
    private String first_name;
    private String second_name;
    private int age;
    private String gender;
    private String curp;
    private String birthdate;
    private String address;
    private String phone_number;
    private String to_name_complete;

    public StudentModel() {
    }

    public StudentModel(int id_student, int id_tutor, String complete_name, String first_name, String second_name, int age, String gender, String curp, String birthdate, String address, String phone_number, String to_name_complete) {
        this.id_student = id_student;
        this.id_tutor = id_tutor;
        this.complete_name = complete_name;
        this.first_name = first_name;
        this.second_name = second_name;
        this.age = age;
        this.gender = gender;
        this.curp = curp;
        this.birthdate = birthdate;
        this.address = address;
        this.phone_number = phone_number;
        this.to_name_complete = to_name_complete;
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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getTo_name_complete() {
        return complete_name + " " + first_name + " " + second_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "StudentModel{" + "id_student=" + id_student + ", id_tutor=" + id_tutor + ", complete_name=" + complete_name + ", first_name=" + first_name + ", second_name=" + second_name + ", age=" + age + ", gender=" + gender + ", curp=" + curp + ", birthdate=" + birthdate + ", address=" + address + ", phone_number=" + phone_number + ", to_name_complete=" + to_name_complete + '}';
    }
}
