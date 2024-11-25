package com.school.system.dao;

import com.school.connection.ConnectionPool;
import com.school.model.TeacherModel;
import com.school.model.GradeGroupModel;
import com.school.model.SubjectModel;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TeacherDAO {

    private List<TeacherModel> listTeachers;
    private List<SubjectModel> listSubjects;
    private List<GradeGroupModel> listGradeGroup;
    private List<SubjectModel> listAssignedSubject;
    private List<SubjectModel> listSubjectByAssign;
    private List<GradeGroupModel> listGradeGroupByAssign;
    private List<GradeGroupModel> listAssignedGradeGroup;

    public int removeAssignSubjectTeacher(int id_subject, int id_teacher) {
        ConnectionPool con = new ConnectionPool();
        int response = 0;
        Statement stm;
        String query = "DELETE FROM centro_teacher_subject\n"
                + "WHERE\n"
                + "        1 = 1\n"
                + "    AND id_teacher = " + id_teacher + "\n"
                + "    AND id_subject = " + id_subject + "";
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

    public List<SubjectModel> getListSubjectByAssign(String id_grade_groups, TeacherModel teacher) {
        listSubjectByAssign = getListSubjects(id_grade_groups);
        for (SubjectModel s : getListAssignedSubject(teacher)) {
            for (SubjectModel su : listSubjectByAssign) {
                if (su.getId_subject() == s.getId_subject()) {
                    listSubjectByAssign.remove(su);
                    break;
                }
            }
        }

        return listSubjectByAssign;
    }

    public List<GradeGroupModel> getListGradeGroupByAssign(TeacherModel teacher) {
        listGradeGroupByAssign = getListGradeGroup();
        for (GradeGroupModel g : getListAssignedGradeGroup(teacher)) {
            for (GradeGroupModel gd : listGradeGroupByAssign) {
                if (gd.getId_grade_group() == g.getId_grade_group()) {
                    listGradeGroupByAssign.remove(gd);
                    break;
                }
            }
        }

        return listGradeGroupByAssign;
    }

    public int assignSubjectTeacher(int subject, int teacher) {
        ConnectionPool con = new ConnectionPool();
        int response = 0;
        Statement stm;
        String query = "INSERT INTO centro_teacher_subject (\n"
                + "    id_teacher,\n"
                + "    id_subject\n"
                + ") VALUES (\n"
                + "    " + teacher + ",\n"
                + "    " + subject + "\n"
                + ")";
        System.out.println(query);
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

    private List<SubjectModel> getListSubjects(String id_grade_groups) {
        ConnectionPool con = new ConnectionPool();
        listSubjects = new ArrayList<>();
        SubjectModel subject;
        ResultSet rst;
        Statement stm;
        String query = "SELECT DISTINCT\n"
                + "    cs.id_subject,\n"
                + "    cs.subject_name,\n"
                + "    gg.id_grade_group,\n"
                + "    gg.grade,\n"
                + "    gg.grad_group\n"
                + "FROM\n"
                + "    centro_subject     cs,\n"
                + "    centro_grade_group gg\n"
                + "WHERE\n"
                + "        1 = 1\n"
                + "    AND gg.id_grade_group IN ( " + id_grade_groups + "0 )\n"
                + "    AND cs.id_grade_group = gg.id_grade_group";
        try {
            stm = con.startConnectionBD().createStatement();
            rst = stm.executeQuery(query);
            while (rst.next()) {
                subject = new SubjectModel(rst.getInt("id_subject"),
                        rst.getString("subject_name"), rst.getInt("id_grade_group"),
                        rst.getString("grade"), rst.getString("grad_group"));
                listSubjects.add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }

        return listSubjects;
    }

    public List<SubjectModel> getListAssignedSubject(TeacherModel teacher) {
        ConnectionPool con = new ConnectionPool();
        listAssignedSubject = new ArrayList<>();
        SubjectModel subject;
        ResultSet rst;
        Statement stm;
        String query = "SELECT DISTINCT\n"
                + "    cs.id_subject,\n"
                + "    cs.subject_name,\n"
                + "    gg.id_grade_group,\n"
                + "    gg.grade,\n"
                + "    gg.grad_group\n"
                + "FROM\n"
                + "    centro_teacher_subject cts,\n"
                + "    centro_grade_group     gg,\n"
                + "    centro_teacher         ct,\n"
                + "    centro_subject         cs\n"
                + "WHERE\n"
                + "        1 = 1\n"
                + "    AND ct.id_teacher = " + teacher.getId_teacher() + "\n"
                + "    AND cts.id_teacher = ct.id_teacher\n"
                + "    AND cs.id_subject = cts.id_subject\n"
                + "    AND cs.id_grade_group = gg.id_grade_group";
        try {
            stm = con.startConnectionBD().createStatement();
            rst = stm.executeQuery(query);
            while (rst.next()) {
                subject = new SubjectModel(rst.getInt("id_subject"),
                        rst.getString("subject_name"), rst.getInt("id_grade_group"),
                        rst.getString("grade"), rst.getString("grad_group"));
                listAssignedSubject.add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }

        return listAssignedSubject;
    }

    public int removeAssignGradeGroupByTeacher(List<GradeGroupModel> listSelectedGradeGroupByRemove, TeacherModel teacher) {
        ConnectionPool con = new ConnectionPool();
        int response = 0;
        Statement stm;
        try {
            stm = con.startConnectionBD().createStatement();
            for (Iterator<GradeGroupModel> it = listSelectedGradeGroupByRemove.iterator(); it.hasNext();) {
                Object obj = it.next();
                String odr = String.valueOf(obj);
                int inicio = odr.indexOf("=");
                int fin = odr.indexOf(",", inicio + 1);
                String query = "DELETE FROM centro_teacher_grade_group\n"
                        + "WHERE\n"
                        + "        1 = 1\n"
                        + "    AND id_teacher = " + teacher.getId_teacher() + "\n"
                        + "    AND id_grade_group = " + odr.substring(inicio + 1, fin) + "";
                response = stm.executeUpdate(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }

        return response;
    }

    public int toAssignGradeGroupByTeacher(List<GradeGroupModel> listSelectedGradeGroupByAssign, TeacherModel teacher) {
        ConnectionPool con = new ConnectionPool();
        int response = 0;
        Statement stm;
        try {
            stm = con.startConnectionBD().createStatement();
            for (Iterator<GradeGroupModel> it = listSelectedGradeGroupByAssign.iterator(); it.hasNext();) {
                Object obj = it.next();
                String odr = String.valueOf(obj);
                int inicio = odr.indexOf("=");
                int fin = odr.indexOf(",", inicio + 1);
                String query = "INSERT INTO centro_teacher_grade_group (\n"
                        + "    id_teacher,\n"
                        + "    id_grade_group\n"
                        + ") VALUES (\n"
                        + "    " + teacher.getId_teacher() + ",\n"
                        + "    " + odr.substring(inicio + 1, fin) + "\n"
                        + ")";
                System.out.println(query);
                response = stm.executeUpdate(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }

        return response;
    }

    private List<GradeGroupModel> getListGradeGroup() {
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

    public List<GradeGroupModel> getListAssignedGradeGroup(TeacherModel teacher) {
        listAssignedGradeGroup = new ArrayList<>();
        ConnectionPool con = new ConnectionPool();
        GradeGroupModel grade_group_assigned;
        ResultSet rst;
        Statement stm;
        String query = "SELECT DISTINCT\n"
                + "    gg.id_grade_group,\n"
                + "    gg.grade,\n"
                + "    gg.grad_group\n"
                + "FROM\n"
                + "    centro_grade_group         gg,\n"
                + "    centro_teacher_grade_group tgg\n"
                + "WHERE\n"
                + "        1 = 1\n"
                + "    AND tgg.id_teacher = " + teacher.getId_teacher() + "\n"
                + "    AND gg.id_grade_group = tgg.id_grade_group";
        try {
            stm = con.startConnectionBD().createStatement();
            rst = stm.executeQuery(query);
            while (rst.next()) {
                grade_group_assigned = new GradeGroupModel(rst.getInt("id_grade_group"),
                        rst.getString("grade"), rst.getString("grad_group"));
                listAssignedGradeGroup.add(grade_group_assigned);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }

        return listAssignedGradeGroup;
    }

    public int updTeacher(TeacherModel teacher) {
        ConnectionPool con = new ConnectionPool();
        int response = 0;
        Statement stm;
        String query = "UPDATE centro_teacher\n"
                + "SET\n"
                + "    complete_name = '" + teacher.getComplete_name() + "',\n"
                + "    address = '" + teacher.getAddress() + "',\n"
                + "    phone_number = '" + teacher.getPhone_number() + "',\n"
                + "    email = '" + teacher.getEmail() + "',\n"
                + "    curp = '" + teacher.getCurp() + "',\n"
                + "    rfc = '" + teacher.getRfc() + "'\n"
                + "WHERE\n"
                + "        1 = 1\n"
                + "    AND id_teacher = " + teacher.getId_teacher() + "";
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

    public int remTeacher(TeacherModel teacher) {
        ConnectionPool con = new ConnectionPool();
        int response = 0;
        Statement stm;
        String query = "DELETE FROM centro_teacher\n"
                + "WHERE\n"
                + "        1 = 1\n"
                + "    AND id_teacher = " + teacher.getId_teacher() + "";
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

    public int addTeacher(TeacherModel teacher) {
        ConnectionPool con = new ConnectionPool();
        int response = 0;
        Statement stm;
        String query = "INSERT INTO centro_teacher (\n"
                + "    complete_name,\n"
                + "    address,\n"
                + "    phone_number,\n"
                + "    email,\n"
                + "    curp,\n"
                + "    rfc,\n"
                + "    birthdate,\n"
                + "    age,\n"
                + "    gender,\n"
                + "    location,\n"
                + "    postal_code,\n"
                + "    photo\n"
                + ") VALUES (\n"
                + "    '" + teacher.getComplete_name() + "',\n"
                + "    '" + teacher.getAddress() + "',\n"
                + "    '" + teacher.getPhone_number() + "',\n"
                + "    '" + teacher.getEmail() + "',\n"
                + "    '" + teacher.getCurp() + "',\n"
                + "    '" + teacher.getRfc() + "',\n"
                + "    '" + teacher.getBirthdate() + "',\n"
                + "    " + teacher.getAge() + ",\n"
                + "    '" + teacher.getGender() + "',\n"
                + "    '" + teacher.getLocation() + "',\n"
                + "    " + teacher.getPostal_code() + ",\n"
                + "    '" + teacher.getPhoto() + "'\n"
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

    public List<TeacherModel> getListTeachers() {
        ConnectionPool con = new ConnectionPool();
        listTeachers = new ArrayList<>();
        TeacherModel teacher;
        Statement stm;
        ResultSet rst;
        String query = "SELECT\n"
                + "    *\n"
                + "FROM\n"
                + "    centro_teacher\n"
                + "ORDER BY\n"
                + "    id_teacher DESC";
        try {
            stm = con.startConnectionBD().createStatement();
            rst = stm.executeQuery(query);
            while (rst.next()) {
                teacher = new TeacherModel(rst.getInt("id_teacher"),
                        rst.getString("complete_name"), rst.getString("address"),
                        rst.getString("phone_number"), rst.getString("email"),
                        rst.getString("curp"), rst.getString("rfc"),
                        rst.getString("birthdate"), rst.getInt("age"),
                        (rst.getString("gender") == null ? ""
                        : (rst.getString("gender").equalsIgnoreCase("M") ? "Masculino"
                        : (rst.getString("gender").equalsIgnoreCase("F") ? "Femenino" : "No aplica"))),
                        rst.getString("location"), rst.getInt("postal_code"), rst.getString("photo"));
                listTeachers.add(teacher);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }

        return listTeachers;
    }
}
