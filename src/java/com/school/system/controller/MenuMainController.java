package com.school.system.controller;

import com.school.model.MenuMainModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "menu_main_school")
@ViewScoped
public class MenuMainController implements Serializable {

    private List<MenuMainModel> listMenuMain;
    private int user_id;

    @PostConstruct
    public void initLoadDataMenu() {
        listMenuMain = new ArrayList<>();
        listMenuMain.add(new MenuMainModel("Alumnos", "Gestión de alumnos", "student_module.jpg"));
        listMenuMain.add(new MenuMainModel("Profesores", "Gestión de profesores", "teacher_module.jpg"));
        listMenuMain.add(new MenuMainModel("Calificaciones", "Gestión de calificaciones", "qualification_module.jpg"));
        listMenuMain.add(new MenuMainModel("Materias", "Gestión de asignaturas", "subject_module.jpg"));
        listMenuMain.add(new MenuMainModel("Grados", "Gestión de grado y grupo", "grade_gruop.jpg"));
    }

    private boolean verificateSession() {
        boolean flag_session = false;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String user = (String) facesContext.getExternalContext().getSessionMap().get("username");
        if (user != null) {
            user_id = (int) facesContext.getExternalContext().getSessionMap().get("user_id");
            flag_session = true;
        }

        return flag_session;
    }

    public List<MenuMainModel> getListMenuMain() {
        return listMenuMain;
    }

    public void setListMenuMain(List<MenuMainModel> listMenuMain) {
        this.listMenuMain = listMenuMain;
    }

}
