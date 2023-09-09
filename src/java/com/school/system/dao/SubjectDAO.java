package com.school.system.dao;

import com.school.connection.ConnectionPool;
import com.school.model.SubjectModel;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {

    private List<SubjectModel> listSubject;

    public int updSubject(SubjectModel subject) {
        ConnectionPool con = new ConnectionPool();
        int response = 0;
        Statement stm;
        String query = "UPDATE centro_subject\n"
                + "SET\n"
                + "    subject_name = '" + subject.getSubject_name() + "',\n"
                + "    id_grade_group = " + subject.getId_grade_group() + "\n"
                + "WHERE\n"
                + "        1 = 1\n"
                + "    AND id_subject = " + subject.getId_subject() + "";
        try {
            stm = con.startConnectionBD().createStatement();
            response = stm.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }

        return response;
    }

    public int remSubject(SubjectModel subject) {
        ConnectionPool con = new ConnectionPool();
        int response = 0;
        Statement stm;
        String query = "DELETE FROM centro_subject\n"
                + "WHERE\n"
                + "        1 = 1\n"
                + "    AND id_subject = " + subject.getId_subject() + "";
        try {
            stm = con.startConnectionBD().createStatement();
            response = stm.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }

        return response;
    }

    public int addSubject(SubjectModel subject) {
        ConnectionPool con = new ConnectionPool();
        int response = 0;
        Statement stm;
        String query = "INSERT INTO centro_subject (\n"
                + "    id_grade_group,\n"
                + "    subject_name\n"
                + ") VALUES (\n"
                + "    " + subject.getId_grade_group() + ",\n"
                + "    '" + subject.getSubject_name() + "'\n"
                + ")";
        try {
            stm = con.startConnectionBD().createStatement();
            response = stm.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }

        return response;
    }

    public List<SubjectModel> getListSubjects() {
        ConnectionPool con = new ConnectionPool();
        listSubject = new ArrayList<>();
        SubjectModel subject;
        ResultSet rst;
        Statement stm;
        String query = "SELECT DISTINCT\n"
                + "    cs.id_subject,\n"
                + "    cs.subject_name,\n"
                + "    cgg.id_grade_group,\n"
                + "    cgg.grade,\n"
                + "    cgg.grad_group\n"
                + "FROM\n"
                + "    centro_subject     cs,\n"
                + "    centro_grade_group cgg\n"
                + "WHERE\n"
                + "        1 = 1\n"
                + "    AND cs.id_grade_group = cgg.id_grade_group\n"
                + "ORDER BY\n"
                + "    cs.id_subject DESC";
        try {
            stm = con.startConnectionBD().createStatement();
            rst = stm.executeQuery(query);
            while (rst.next()) {
                subject = new SubjectModel(rst.getInt("id_subject"),
                        rst.getString("subject_name"), rst.getInt("id_grade_group"),
                        rst.getString("grade"), rst.getString("grad_group"));
                listSubject.add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }

        return listSubject;
    }
}
