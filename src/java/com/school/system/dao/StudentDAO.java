package com.school.system.dao;

import com.school.connection.ConnectionPool;
import com.school.model.GradeGroupModel;
import com.school.model.ParamsStudentModel;
import com.school.model.StudentModel;
import com.school.model.TutorModel;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private List<StudentModel> listStudents;
    private List<GradeGroupModel> listGradeGroup;

    public List<StudentModel> getListStudents(ParamsStudentModel params) {
        ConnectionPool con = new ConnectionPool();
        listStudents = new ArrayList<>();
        StudentModel student;
        Statement stm;
        ResultSet rst;
        String query = "SELECT DISTINCT\n"
                + "    cs.*,\n"
                + "    cgg.grade,\n"
                + "    cgg.grad_group,\n"
                + "    ct.email         email_tutor,\n"
                + "    ct.phone_number  phone_number_tutor,\n"
                + "    ct.complete_name name_tutor,\n"
                + "    ct.first_name    first_name_tutor,\n"
                + "    ct.second_name   second_name_tutor\n"
                + "FROM\n"
                + "    centro_student             cs,\n"
                + "    centro_student_grade_group csgg,\n"
                + "    centro_grade_group         cgg,\n"
                + "    centro_tutor               ct\n"
                + "WHERE\n"
                + "        1 = 1\n";

        if (!params.getName_student().equalsIgnoreCase("")) {
            query += "    AND upper(concat(cs.complete_name, ' ', cs.first_name, ' ', cs.second_name)) LIKE '%" + params.getName_student().toUpperCase() + "%'\n";
        }

        if (!params.getTuition_number().equalsIgnoreCase("")) {
            query += "    AND upper(cs.tuition_number) LIKE '%" + params.getTuition_number().toUpperCase() + "%'\n";
        }

        if (!params.getCurp().equalsIgnoreCase("")) {
            query += "    AND upper(cs.curp) LIKE '%" + params.getCurp().toUpperCase() + "%'\n";
        }

        if (params.getList_grade_group() != null || params.getList_grade_group().isEmpty()) {
            String id_gg = "";
            for (String gg : params.getList_grade_group()) {
                id_gg += gg + ",";
            }

            if (!id_gg.equalsIgnoreCase("")) {
                query += "    AND cgg.id_grade_group IN ( " + id_gg + "0 )\n";
            }
        }

        query += "    AND ct.id_tutor = cs.id_tutor\n"
                + "    AND csgg.id_student = cs.id_student\n"
                + "    AND csgg.id_grade_group = cgg.id_grade_group";
        System.out.println(query);
        try {
            stm = con.startConnectionBD().createStatement();
            rst = stm.executeQuery(query);
            while (rst.next()) {
                student = new StudentModel(rst.getInt("id_student"), rst.getInt("id_tutor"),
                        rst.getString("complete_name"), rst.getString("first_name"),
                        rst.getString("second_name"), rst.getInt("age"), rst.getString("gender"),
                        rst.getString("curp"), rst.getString("birthdate"), rst.getString("address"),
                        rst.getString("phone_number"), rst.getString("email"),
                        new GradeGroupModel(rst.getString("grade"), rst.getString("grad_group")),
                        new TutorModel(rst.getString("name_tutor"), rst.getString("first_name_tutor"),
                                rst.getString("second_name_tutor"), rst.getString("email_tutor"),
                                rst.getString("phone_number_tutor")));
                listStudents.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }

        return listStudents;
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
