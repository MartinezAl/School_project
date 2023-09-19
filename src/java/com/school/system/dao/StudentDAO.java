package com.school.system.dao;

import com.school.connection.ConnectionPool;
import com.school.model.StudentModel;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private List<StudentModel> listStudents;

    public List<StudentModel> getListStudents() {
        ConnectionPool con = new ConnectionPool();
        listStudents = new ArrayList<>();
        StudentModel student;
        
        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }

        return listStudents;
    }
}
