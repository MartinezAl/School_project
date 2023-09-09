package com.school.system.controller;

import com.school.system.dao.GradeGroupDAO;
import com.school.model.GradeGroupModel;
import com.school.model.GradeGroupTeacherModel;
import com.school.model.SubjectModel;
import com.school.system.dao.SubjectDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "grade_group_subject_school")
@ViewScoped
public class GradeGroupSubjectController implements Serializable {

    private List<GradeGroupTeacherModel> listGradeGroupTeacher;
    private List<GradeGroupModel> listGradeGroup;
    private GradeGroupTeacherModel gra_gro_tea;
    private GradeGroupModel grade_group_aux;
    private List<SubjectModel> listSubject;
    private GradeGroupModel grade_group;
    private GradeGroupDAO gradeGroupDAO;
    private SubjectModel subject_aux;
    private SubjectDAO subjectDAO;
    private SubjectModel subject;
    private int grade;

    @PostConstruct
    public void initLoadData() {
        subject = new SubjectModel();
        subjectDAO = new SubjectDAO();
        listSubject = new ArrayList<>();
        subject_aux = new SubjectModel();
        listGradeGroup = new ArrayList<>();
        gradeGroupDAO = new GradeGroupDAO();
        grade_group = new GradeGroupModel();
        listSubject = subjectDAO.getListSubjects();
        listGradeGroup = gradeGroupDAO.getListGradeGroup();
    }

    private void initLoadSubject() {
        listSubject = new ArrayList<>();
        listSubject = subjectDAO.getListSubjects();
    }

    private void initLoadGradeGroup() {
        listGradeGroup = new ArrayList<>();
        listGradeGroup = gradeGroupDAO.getListGradeGroup();
    }

    private boolean validateExistSubject(SubjectModel subject) {
        boolean flag = false;
        for (SubjectModel s : listSubject) {
            if (s.getSubject_name().toLowerCase().equalsIgnoreCase(subject.getSubject_name().toLowerCase())
                    && s.getId_grade_group() == subject.getId_grade_group()) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    private void cleanGradeGroup() {
        grade = 0;
        subject = new SubjectModel();
        grade_group = new GradeGroupModel();
    }

    public void assignTeacherGraGro(GradeGroupTeacherModel _gra_gro_tea) {
        gra_gro_tea = _gra_gro_tea;
    }

    public void remAssignedTeacherGradeGroup() {
        if (gradeGroupDAO.remAssignedTeacherGradeGroup(gra_gro_tea) != 0) {
            loadAssignedTeacherGradeGroup(grade_group_aux);
            viewMessageLaunch("El profesor ha sido eliminada de la asignación del grupo.", "Exito! ", "Success");
        } else {
            viewMessageLaunch("Ha ocurrido un error al eliminar la asignación.", "Error! ", "Error");
        }
    }

    public void loadAssignedTeacherGradeGroup(GradeGroupModel grade_group) {
        grade_group_aux = grade_group;
        listGradeGroupTeacher = new ArrayList<>();
        listGradeGroupTeacher = gradeGroupDAO.getListGradeGroupTeachers(grade_group);
    }

    public void updSubject() {
        if (subject_aux.getSubject_name() == null || subject_aux.getSubject_name().equalsIgnoreCase("")) {
            viewMessageLaunch("Asegurese de poner la información solicitada.", "Error! ", "Error");
        } else {
            if (subjectDAO.updSubject(subject_aux) != 0) {
                initLoadSubject();
                viewMessageLaunch("La información de la materia / asignatura ha sido actualizada.", "Exito! ", "Success");
            } else {
                viewMessageLaunch("Ha ocurrido un error al eliminar la materia / asignatura.", "Error! ", "Error");
            }
        }
    }

    public void remSubject() {
        if (subjectDAO.remSubject(subject_aux) != 0) {
            initLoadSubject();
            viewMessageLaunch("La materia / asignatura ha sido eliminada.", "Exito! ", "Success");
        } else {
            viewMessageLaunch("Ha ocurrido un error al eliminar la materia / asignatura.", "Error! ", "Error");
        }
    }

    public void addSubject() {
        if (subject.getSubject_name() == null || subject.getSubject_name().equalsIgnoreCase("")
                || subject.getId_grade_group() == 0) {
            viewMessageLaunch("Asegurese de poner la información solicitada.", "Error! ", "Error");
        } else {
            if (validateExistSubject(subject)) {
                viewMessageLaunch("La materia / asignatura ya existe.", "Importante! ", "Warning");
            } else {
                if (subjectDAO.addSubject(subject) != 0) {
                    initLoadSubject();
                    cleanGradeGroup();
                    viewMessageLaunch("La materia / asignatura ha sido registrada de manera correcta.", "Exito! ", "Success");
                } else {
                    viewMessageLaunch("Ha ocurrido un error al registrar la materia / asignatura.", "Error! ", "Error");
                }
            }
        }
    }

    public void setbySubject(SubjectModel subject) {
        subject_aux = subject;
    }

    public void setbyGradeGroup(GradeGroupModel grade_group) {
        grade_group_aux = grade_group;
    }

    public void remGradeGroup() {
        if (gradeGroupDAO.remGradeGroup(grade_group_aux) != 0) {
            initLoadGradeGroup();
            viewMessageLaunch("El grado, grupo ha sido eliminado.", "Exito! ", "Success");
        } else {
            viewMessageLaunch("Hubo un error al eliminar el grado grupo.", "Aviso! ", "Error");
        }
    }

    private boolean validateGradeGroupExist(GradeGroupModel grade_group) {
        boolean flag = false;
        for (GradeGroupModel g : listGradeGroup) {
            if (g.getTo_desc().replaceAll("\\°", "").equalsIgnoreCase(grade_group.getTo_desc())) {
                flag = true;
            }
        }

        return flag;
    }

    public void addGradeGroup() {
        if (grade_group.getGrad_group() == null || grade_group.getGrad_group().equalsIgnoreCase("")
                || grade == 0) {
            viewMessageLaunch("Asegurese de poner información requerida.", "Aviso! ", "Error");
        } else {
            grade_group.setGrade(String.valueOf(grade));
            if (validateGradeGroupExist(grade_group)) {
                viewMessageLaunch("El grado / grupo ya existe.", "Importante! ", "Warning");
            } else {
                if (gradeGroupDAO.addGradeGroup(grade_group) != 0) {
                    initLoadGradeGroup();
                    cleanGradeGroup();
                    viewMessageLaunch("El grado, grupo ha sido registrado.", "Exito! ", "Success");
                } else {
                    viewMessageLaunch("Ocurrio un error al registrar el grado y grupo.", "Aviso! ", "Error");
                }
            }
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

    public List<GradeGroupModel> getListGradeGroup() {
        return listGradeGroup;
    }

    public void setListGradeGroup(List<GradeGroupModel> listGradeGroup) {
        this.listGradeGroup = listGradeGroup;
    }

    public GradeGroupModel getGrade_group() {
        return grade_group;
    }

    public void setGrade_group(GradeGroupModel grade_group) {
        this.grade_group = grade_group;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public GradeGroupModel getGrade_group_aux() {
        return grade_group_aux;
    }

    public void setGrade_group_aux(GradeGroupModel grade_group_aux) {
        this.grade_group_aux = grade_group_aux;
    }

    public List<SubjectModel> getListSubject() {
        return listSubject;
    }

    public void setListSubject(List<SubjectModel> listSubject) {
        this.listSubject = listSubject;
    }

    public SubjectModel getSubject_aux() {
        return subject_aux;
    }

    public void setSubject_aux(SubjectModel subject_aux) {
        this.subject_aux = subject_aux;
    }

    public SubjectModel getSubject() {
        return subject;
    }

    public void setSubject(SubjectModel subject) {
        this.subject = subject;
    }

    public List<GradeGroupTeacherModel> getListGradeGroupTeacher() {
        return listGradeGroupTeacher;
    }

    public void setListGradeGroupTeacher(List<GradeGroupTeacherModel> listGradeGroupTeacher) {
        this.listGradeGroupTeacher = listGradeGroupTeacher;
    }

    public GradeGroupTeacherModel getGra_gro_tea() {
        return gra_gro_tea;
    }

    public void setGra_gro_tea(GradeGroupTeacherModel gra_gro_tea) {
        this.gra_gro_tea = gra_gro_tea;
    }
}
