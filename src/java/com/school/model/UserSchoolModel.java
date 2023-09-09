package com.school.model;

import java.io.Serializable;

public class UserSchoolModel implements Serializable {

    private int id_user;
    private int id_rol;
    private String rol_desc;
    private String username;
    private String password;
    private String username_complete;

    public UserSchoolModel() {
    }

    public UserSchoolModel(int id_user, int id_rol, String rol_desc, String username, String password, String username_complete) {
        this.id_user = id_user;
        this.id_rol = id_rol;
        this.rol_desc = rol_desc;
        this.username = username;
        this.password = password;
        this.username_complete = username_complete;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getRol_desc() {
        return rol_desc;
    }

    public void setRol_desc(String rol_desc) {
        this.rol_desc = rol_desc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername_complete() {
        return username_complete;
    }

    public void setUsername_complete(String username_complete) {
        this.username_complete = username_complete;
    }

    @Override
    public String toString() {
        return "UserSchoolModel{" + "id_user=" + id_user + ", id_rol=" + id_rol + ", rol_desc=" + rol_desc + ", username=" + username + ", password=" + password + ", username_complete=" + username_complete + '}';
    }
}
