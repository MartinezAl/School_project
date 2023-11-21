package com.school.system.controller;

import com.school.model.GradeGroupModel;
import com.school.model.ParamsStudentModel;
import com.school.model.StudentModel;
import com.school.system.dao.StudentDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "student_school")
@ViewScoped
public class StudentContoller implements Serializable {

    private List<GradeGroupModel> listGradeGroup;
    private List<String> listDataGradeGroup;
    private List<StudentModel> listStudents;
    private List<String> list_grade_group;
    private ParamsStudentModel params;
    private StudentDAO studentDAO;

    @PostConstruct
    public void initLoadData() {
        studentDAO = new StudentDAO();
        listStudents = new ArrayList<>();
        params = new ParamsStudentModel();
        listGradeGroup = new ArrayList<>();
        list_grade_group = new ArrayList<>();
        listDataGradeGroup = new ArrayList<>();
        listGradeGroup = studentDAO.getListGradeGroup();
        loadDataGradeGroup();
    }

    public void initLoadDataStudent() {
        if (validateParamsValueEmpty()) {
            viewMessageLaunch("Debe ingresar valores para la busqueda de alumno(s).", "Error! ", "Error");
        } else {
            listStudents = new ArrayList<>();
            listStudents = studentDAO.getListStudents(params);
        }
    }

    private boolean validateParamsValueEmpty() {
        boolean flag = false;
        System.out.println("valores : " + params.toString());
        if ((params.getList_grade_group() == null || params.getList_grade_group().isEmpty())
                && params.getName_student().equalsIgnoreCase("")
                && params.getTuition_number().equalsIgnoreCase("")
                && params.getCurp().equalsIgnoreCase("")) {
            flag = true;
        }

        return flag;
    }

    public void setIdGradeGroup() {
        List<String> _list_id_gg = new ArrayList<>();
        for (String s : list_grade_group) {
            for (GradeGroupModel gg : listGradeGroup) {
                if (s.equalsIgnoreCase(gg.getTo_desc())) {
                    _list_id_gg.add(String.valueOf(gg.getId_grade_group()));
                    break;
                }
            }
        }
        System.out.println("valor de grupos : " + _list_id_gg);
        params.setList_grade_group(_list_id_gg);
    }

    private void loadDataGradeGroup() {
        for (GradeGroupModel g : listGradeGroup) {
            listDataGradeGroup.add(g.getTo_desc());
        }
    }

    private void viewMessageLaunch(String mess, String messClass, String verifyMess) {
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(
                        (verifyMess.equalsIgnoreCase("Success") ? FacesMessage.SEVERITY_INFO
                        : verifyMess.equalsIgnoreCase("Warning") ? FacesMessage.SEVERITY_WARN
                        : verifyMess.equalsIgnoreCase("Error") ? FacesMessage.SEVERITY_ERROR : FacesMessage.SEVERITY_INFO),
                        messClass, mess));
    }

    public List<String> getListDataGradeGroup() {
        return listDataGradeGroup;
    }

    public void setListDataGradeGroup(List<String> listDataGradeGroup) {
        this.listDataGradeGroup = listDataGradeGroup;
    }

    public List<String> getList_grade_group() {
        return list_grade_group;
    }

    public void setList_grade_group(List<String> list_grade_group) {
        this.list_grade_group = list_grade_group;
    }

    public ParamsStudentModel getParams() {
        return params;
    }

    public void setParams(ParamsStudentModel params) {
        this.params = params;
    }

    public List<StudentModel> getListStudents() {
        return listStudents;
    }

    public void setListStudents(List<StudentModel> listStudents) {
        this.listStudents = listStudents;
    }
}
