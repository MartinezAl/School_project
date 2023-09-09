package com.school.session.dao;

import com.school.connection.ConnectionPool;
import com.school.model.UserSchoolModel;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SchoolSessionDAO {

    private List<UserSchoolModel> listUser;

    public List<UserSchoolModel> getListUsers() {
        listUser = new ArrayList<>();
        ConnectionPool con = new ConnectionPool();
        Statement stm;
        ResultSet rst;
        UserSchoolModel user;
        String query = "SELECT\n"
                + "    *\n"
                + "FROM\n"
                + "    centro_users\n"
                + "ORDER BY\n"
                + "    id_user DESC";
        try {
            stm = con.startConnectionBD().createStatement();
            rst = stm.executeQuery(query);
            while (rst.next()) {
                user = new UserSchoolModel(rst.getInt("id_user"), rst.getInt("id_rol"), "",
                        rst.getString("username"), "", rst.getString("complete_username"));
                listUser.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }

        return listUser;
    }

    public boolean validateUser(String password) {
        boolean flag = false;
        ConnectionPool con = new ConnectionPool();
        Statement stm;
        ResultSet rst;
        String query = "SELECT\n"
                + "    1 exist\n"
                + "FROM\n"
                + "    centro_users\n"
                + "WHERE\n"
                + "        1 = 1\n"
                + "    AND password = md5('" + password + "')\n"
                + "ORDER BY\n"
                + "    id_user DESC";
        try {
            stm = con.startConnectionBD().createStatement();
            rst = stm.executeQuery(query);
            flag = rst.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }

        return flag;
    }
}
