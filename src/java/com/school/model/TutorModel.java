package com.school.model;

import java.io.Serializable;

public class TutorModel implements Serializable {

    private int id_tutor;
    private String complete_name;
    private String first_name;
    private String second_name;
    private String email;
    private String phone_number;

    public TutorModel() {
    }

    public TutorModel(int id_tutor, String complete_name, String first_name, String second_name, String email, String phone_number) {
        this.id_tutor = id_tutor;
        this.complete_name = complete_name;
        this.first_name = first_name;
        this.second_name = second_name;
        this.email = email;
        this.phone_number = phone_number;
    }

    public TutorModel(String complete_name, String first_name, String second_name, String email, String phone_number) {
        this.complete_name = complete_name;
        this.first_name = first_name;
        this.second_name = second_name;
        this.email = email;
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
        return "TutorModel{" + "id_tutor=" + id_tutor + ", complete_name=" + complete_name + ", first_name=" + first_name + ", second_name=" + second_name + ", email=" + email + ", phone_number=" + phone_number + '}';
    }
}
