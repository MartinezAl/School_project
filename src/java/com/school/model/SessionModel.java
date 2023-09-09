package com.school.model;

import java.io.Serializable;

public class SessionModel implements Serializable {

    private boolean flag;
    private UserSchoolModel user;

    public SessionModel() {
    }

    public SessionModel(boolean flag, UserSchoolModel user) {
        this.flag = flag;
        this.user = user;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public UserSchoolModel getUser() {
        return user;
    }

    public void setUser(UserSchoolModel user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SessionModel{" + "flag=" + flag + ", user=" + user + '}';
    }
}
