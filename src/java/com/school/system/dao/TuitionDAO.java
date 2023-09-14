package com.school.system.dao;

import com.school.connection.ConnectionPool;
import com.school.model.TuitionModel;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TuitionDAO {

    private List<TuitionModel> listTuitions;

    public int removePeriodTuition(TuitionModel tuition) {
        ConnectionPool con = new ConnectionPool();
        int response = 0;
        Statement stm;
        String query = "DELETE FROM centro_tuition\n"
                + "WHERE\n"
                + "        1 = 1\n"
                + "    AND id_tituition = " + tuition.getId_tituition() + "";
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

    public int closePeriodTuition(TuitionModel tuition) {
        ConnectionPool con = new ConnectionPool();
        int response = 0;
        Statement stm;
        String query = "UPDATE centro_tuition\n"
                + "SET\n"
                + "    flag_active = false\n"
                + "WHERE\n"
                + "        1 = 1\n"
                + "    AND id_tituition = " + tuition.getId_tituition() + "";
        
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

    public int addTuition(TuitionModel tuition) {
        ConnectionPool con = new ConnectionPool();
        int response = 0;
        Statement stm;
        String query = "INSERT INTO centro_tuition (\n"
                + "    amount,\n"
                + "    period_init,\n"
                + "    period_final,\n"
                + "    flag_active\n"
                + ") VALUES (\n"
                + "    " + tuition.getAmount() + ",\n"
                + "    '" + tuition.getPeriod_init() + "',\n"
                + "    '" + tuition.getPeriod_final() + "',\n"
                + "    true\n"
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

    public List<TuitionModel> getListTuitions() {
        ConnectionPool con = new ConnectionPool();
        listTuitions = new ArrayList<>();
        Statement stm;
        ResultSet rst;
        TuitionModel tuition;
        String query = "SELECT\n"
                + "    *\n"
                + "FROM\n"
                + "    centro_tuition\n"
                + "WHERE\n"
                + "    1 = 1\n"
                + "ORDER BY\n"
                + "    id_tituition DESC";
        try {
            stm = con.startConnectionBD().createStatement();
            rst = stm.executeQuery(query);
            while (rst.next()) {
                tuition = new TuitionModel(rst.getInt("id_tituition"),
                        rst.getDouble("amount"), rst.getString("period_init"),
                        rst.getString("period_final"),
                        (rst.getString("flag_active").equalsIgnoreCase("1")),
                        (rst.getString("flag_active").equalsIgnoreCase("1") ? "Abierto" : "Cerrado")
                );
                listTuitions.add(tuition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }

        return listTuitions;
    }
}
