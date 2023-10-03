package com.school.model;

import java.io.Serializable;

public class TutorModel implements Serializable {

    private int id_tutor;
    private String complete_name;
    private String email;
    private String phone_number;

    public TutorModel() {
    }

    public TutorModel(int id_tutor, String complete_name, String email, String phone_number) {
        this.id_tutor = id_tutor;
        this.complete_name = complete_name;
        this.email = email;
        this.phone_number = phone_number;
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
        return "TutorModel{" + "id_tutor=" + id_tutor + ", complete_name=" + complete_name + ", email=" + email + ", phone_number=" + phone_number + '}';
    }
}
