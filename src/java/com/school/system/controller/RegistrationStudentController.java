package com.school.system.controller;

import com.school.model.StudentModel;
import com.school.model.TutorModel;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "registration_school")
@ViewScoped
public class RegistrationStudentController implements Serializable {

    private TutorModel tutor_student;
    private StudentModel student;
    private Date birthdate;

    @PostConstruct
    public void initLoadRegistration() {
        tutor_student = new TutorModel();
        student = new StudentModel();
    }

    public TutorModel getTutor_student() {
        return tutor_student;
    }

    public void setTutor_student(TutorModel tutor_student) {
        this.tutor_student = tutor_student;
    }

    public StudentModel getStudent() {
        return student;
    }

    public void setStudent(StudentModel student) {
        this.student = student;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
