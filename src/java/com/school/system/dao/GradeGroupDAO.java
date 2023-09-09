package com.school.system.dao;

import com.school.connection.ConnectionPool;
import com.school.model.GradeGroupModel;
import com.school.model.GradeGroupTeacherModel;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GradeGroupDAO {

    private List<GradeGroupModel> listGradeGroup;
    private List<GradeGroupTeacherModel> listGradeGroupTeacher;

    public List<GradeGroupTeacherModel> getListGradeGroupTeachers(GradeGroupModel grade_group) {
        ConnectionPool con = new ConnectionPool();
        listGradeGroupTeacher = new ArrayList<>();
        GradeGroupTeacherModel grade_group_tea;
        Statement stm;
        ResultSet rst;
        String query = "SELECT DISTINCT\n"
                + "    ctgg.id_teacher_gra_gro,\n"
                + "    ct.id_teacher,\n"
                + "    ct.complete_name,\n"
                + "    ct.email,\n"
                + "    ct.phone_number\n"
                + "FROM\n"
                + "    centro_teacher_grade_group ctgg,\n"
                + "    centro_teacher             ct,\n"
                + "    centro_grade_group         cgg\n"
                + "WHERE\n"
                + "        1 = 1\n"
                + "    AND cgg.id_grade_group = " + grade_group.getId_grade_group() + "\n"
                + "    AND ctgg.id_grade_group = cgg.id_grade_group\n"
                + "    AND ct.id_teacher = ctgg.id_teacher";
        try {
            stm = con.startConnectionBD().createStatement();
            rst = stm.executeQuery(query);
            while (rst.next()) {
                grade_group_tea = new GradeGroupTeacherModel(rst.getInt("id_teacher_gra_gro"),
                        rst.getInt("id_teacher"), rst.getString("complete_name"),
                        rst.getString("email"), rst.getString("phone_number"));
                listGradeGroupTeacher.add(grade_group_tea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }

        return listGradeGroupTeacher;
    }

    public int remAssignedTeacherGradeGroup(GradeGroupTeacherModel gra_gro_tea) {
        ConnectionPool con = new ConnectionPool();
        int response = 0;
        String query = "DELETE FROM centro_teacher_grade_group\n"
                + "WHERE\n"
                + "        1 = 1\n"
                + "    AND id_teacher_gra_gro = " + gra_gro_tea.getId_teacher_gra_gro() + "";
        Statement stm;
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

    public int updGradeGroup(GradeGroupModel grade_group) {
        ConnectionPool con = new ConnectionPool();
        int response = 0;
        String query = "DELETE FROM centro_grade_group\n"
                + "WHERE\n"
                + "        1 = 1\n"
                + "    AND id_grade_group = " + grade_group.getId_grade_group() + "";
        Statement stm;
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

    public int remGradeGroup(GradeGroupModel grade_group) {
        ConnectionPool con = new ConnectionPool();
        int response = 0;
        String query = "DELETE FROM centro_grade_group\n"
                + "WHERE\n"
                + "        1 = 1\n"
                + "    AND id_grade_group = " + grade_group.getId_grade_group() + "";
        Statement stm;
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

    public int addGradeGroup(GradeGroupModel grade_group) {
        ConnectionPool con = new ConnectionPool();
        int response = 0;
        String query = "INSERT INTO centro_grade_group (\n"
                + "    grade,\n"
                + "    grad_group\n"
                + ") VALUES (\n"
                + "    '" + grade_group.getGrade() + "°',\n"
                + "    '" + grade_group.getGrad_group() + "'\n"
                + ")";
        Statement stm;
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

    public List<GradeGroupModel> getListGradeGroup() {
        ConnectionPool con = new ConnectionPool();
        listGradeGroup = new ArrayList<>();
        Statement stm;
        ResultSet rst;
        GradeGroupModel grade_group;
        String query = "SELECT\n"
                + "    *\n"
                + "FROM\n"
                + "    centro_grade_group\n"
                + "WHERE\n"
                + "    1 = 1\n"
                + "ORDER BY\n"
                + "    id_grade_group DESC";
        try {
            stm = con.startConnectionBD().createStatement();
            rst = stm.executeQuery(query);
            while (rst.next()) {
                grade_group = new GradeGroupModel(rst.getInt("id_grade_group"),
                        rst.getString("grade"), rst.getString("grad_group"));

                listGradeGroup.add(grade_group);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }

        return listGradeGroup;
    }
}
