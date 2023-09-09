package com.school.session.controller;

import com.school.model.SessionModel;
import com.school.model.UserSchoolModel;
import com.school.session.dao.SchoolSessionDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "session_school")
@ViewScoped
public class SchoolSessionController implements Serializable {

    private int rol_id;
    private UserSchoolModel user;
    private String username;
    private String password;
    private List<UserSchoolModel> listUser;
    private SchoolSessionDAO schoolDAO;

    @PostConstruct
    public void initLoadSession() {
        user = new UserSchoolModel();
        listUser = new ArrayList<>();
        schoolDAO = new SchoolSessionDAO();
        listUser = schoolDAO.getListUsers();
        username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        rol_id = (int) (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("rol_id") == null ? 0
                : FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("rol_id"));
    }

    public void cancelFirstUser() {
        user = new UserSchoolModel();
        password = "";
    }

    private void loadInitSession() {
        password = "";
        user = new UserSchoolModel();
        listUser = new ArrayList<>();
        listUser = schoolDAO.getListUsers();
    }

    public String startSessionApplication() {
        String redirect = null;
        if (username == null || username.equalsIgnoreCase("")) {
            viewMessageLaunch("Ingrese un nombre de usuario", "Aviso! ", "Error");
        } else {
            if (password == null || password.equalsIgnoreCase("")) {
                viewMessageLaunch("Ingrese una contraseña", "Aviso! ", "Error");
            } else {
                SessionModel session = validateSession();
                if (session.isFlag()) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", session.getUser().getUsername_complete());
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user_id", session.getUser().getId_user());
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rol_id", session.getUser().getId_rol());
                    redirect = "/System_views/view_main_menu?faces-redirect=true";
                } else {
                    viewMessageLaunch("El usuario o contraseña son incorrectos", "Aviso! ", "Error");
                }
            }
        }

        return redirect;
    }

    public void closeSession() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/SanSebastianMiscProject");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SessionModel validateSession() {
        boolean flag_pass = false;
        boolean flag_user = false;
        SessionModel session = null;
        UserSchoolModel user = null;
        for (UserSchoolModel u : listUser) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                user = u;
                flag_user = true;
                break;
            }
        }
        flag_pass = schoolDAO.validateUser(password);
        if (flag_user && flag_pass) {
            session = new SessionModel(true, user);
        } else {
            session = new SessionModel(false, user);
        }

        return session;
    }

    private void viewMessageLaunch(String mess, String messClass, String verifyMess) {
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(
                        (verifyMess.equalsIgnoreCase("Success") ? FacesMessage.SEVERITY_INFO
                        : verifyMess.equalsIgnoreCase("Warning") ? FacesMessage.SEVERITY_WARN
                        : verifyMess.equalsIgnoreCase("Error") ? FacesMessage.SEVERITY_ERROR : FacesMessage.SEVERITY_INFO),
                        messClass, mess));
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

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    public List<UserSchoolModel> getListUser() {
        return listUser;
    }

    public void setListUser(List<UserSchoolModel> listUser) {
        this.listUser = listUser;
    }

    public UserSchoolModel getUser() {
        return user;
    }

    public void setUser(UserSchoolModel user) {
        this.user = user;
    }
}
