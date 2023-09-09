package com.school.system.controller;

import com.school.model.GradeGroupModel;
import com.school.model.SubjectModel;
import com.school.system.dao.TeacherDAO;
import com.school.model.TeacherModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "teacher_school")
@ViewScoped
public class TeacherController implements Serializable {

    private List<GradeGroupModel> listSelectedGradeGroupByAssign;
    private List<GradeGroupModel> listSelectedGradeGroupByRemove;
    private List<GradeGroupModel> listGradeGroupByAssign;
    private List<GradeGroupModel> listAssignedGradeGroup;
    private List<SubjectModel> listAssignedSubject;
    private List<SubjectModel> listSubjectByAssign;
    private List<TeacherModel> listTeachers;
    private TeacherModel teacher_aux;
    private TeacherDAO teacherDAO;
    private TeacherModel teacher;

    @PostConstruct
    public void initLoadData() {
        teacher = new TeacherModel();
        teacherDAO = new TeacherDAO();
        teacher_aux = new TeacherModel();
        listTeachers = new ArrayList<>();
        listTeachers = teacherDAO.getListTeachers();
    }

    @SuppressWarnings("UnusedAssignment")
    public void loadSubjectByTeacher(TeacherModel teacher) {
        String id_grade_group = "";
        listAssignedSubject = new ArrayList<>();
        listSubjectByAssign = new ArrayList<>();
        id_grade_group = listAssignedGradeGroup.stream().map((gg) -> gg.getId_grade_group() + ",").reduce(id_grade_group, String::concat);
        listAssignedSubject = teacherDAO.getListAssignedSubject(teacher);
        listSubjectByAssign = teacherDAO.getListSubjectByAssign(id_grade_group, teacher);
    }

    public void loadGradeGroupByTeacher(TeacherModel teacher) {
        teacher_aux = teacher;
        teacherDAO = new TeacherDAO();
        listGradeGroupByAssign = new ArrayList<>();
        listAssignedGradeGroup = new ArrayList<>();
        listAssignedGradeGroup = teacherDAO.getListAssignedGradeGroup(teacher);
        listGradeGroupByAssign = teacherDAO.getListGradeGroupByAssign(teacher);
        loadSubjectByTeacher(teacher);
    }

    public void removeAssignSubjectTeacher(int id_subject) {
        if (teacherDAO.removeAssignSubjectTeacher(id_subject, teacher_aux.getId_teacher()) != 0) {
            loadSubjectByTeacher(teacher_aux);
            viewMessageLaunch("Asignación eliminada de manera correcta.", "Exito! ", "Success");
        } else {
            viewMessageLaunch("Ocurrio un error al eliminar la asignación.", "Error! ", "Error");
        }
    }

    public void removeAssignGradeGroupTeacher() {
        if (listSelectedGradeGroupByRemove.isEmpty()) {
            viewMessageLaunch("Debe seleccionar almenos un grado / grupo.", "Importante! ", "Warning");
        } else {
            if (validateRemoveAssignedGradeGroup()) {
                viewMessageLaunch("No puede eliminar la asignación de los grupos seleccionados, "
                        + "el profesor cuenta con asignación de materias del grado / grupo que quiere eliminar.", "Advertencia! ", "Warning");
            } else {
                if (teacherDAO.removeAssignGradeGroupByTeacher(listSelectedGradeGroupByRemove, teacher_aux) != 0) {
                    loadGradeGroupByTeacher(teacher_aux);
                    loadSubjectByTeacher(teacher_aux);
                    viewMessageLaunch("Se han eliminado los grupos asignados al profesor.", "Exito! ", "Success");
                } else {
                    viewMessageLaunch("Ocurrio un error, no se eliminaron los grupos de manera completa.", "Aviso! ", "Warning");
                }
            }
        }
    }

    public void addToAssignGradeGroupTeacher() {
        if (teacherDAO.toAssignGradeGroupByTeacher(listSelectedGradeGroupByAssign, teacher_aux) != 0) {
            loadGradeGroupByTeacher(teacher_aux);
            loadSubjectByTeacher(teacher_aux);
            viewMessageLaunch("Se han asignado los grupos seleccionados al profesor.", "Exito! ", "Success");
        } else {
            viewMessageLaunch("Ocurrio un error, no se asignaron los grupos de manera completa.", "Aviso! ", "Warning");
        }
    }

    public void assignSubjectTeacher(SubjectModel subject) {
        if (teacherDAO.assignSubjectTeacher(subject.getId_subject(), teacher_aux.getId_teacher()) != 0) {
            loadSubjectByTeacher(teacher_aux);
            viewMessageLaunch("Materia asignada de manera correcta.", "Exito! ", "Success");
        } else {
            viewMessageLaunch("Ocurrio un error, al asignar la materia.", "Error! ", "Error");
        }
    }

    private boolean validateTeacherExist(TeacherModel teacher) {
        boolean flag = false;
        for (TeacherModel t : listTeachers) {
            if (t.getCurp().equalsIgnoreCase(teacher.getCurp())
                    || t.getEmail().equalsIgnoreCase(teacher.getEmail())
                    || t.getRfc().equalsIgnoreCase(teacher.getRfc())) {
                flag = true;
            }
        }

        return flag;
    }

    private boolean validateRemoveAssignedGradeGroup() {
        boolean flag = false;
        for (Iterator<GradeGroupModel> it = listSelectedGradeGroupByRemove.iterator(); it.hasNext();) {
            Object obj = it.next();
            String odr = String.valueOf(obj);
            int inicio = odr.indexOf("=");
            int fin = odr.indexOf(",", inicio + 1);
            for (SubjectModel s : listAssignedSubject) {
                if (s.getId_grade_group() == Integer.parseInt(odr.substring(inicio + 1, fin))) {
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }

    private boolean validateInfoFrmTeacher(TeacherModel teacher) {
        boolean flag = false;
        if (teacher.getComplete_name() == null || teacher.getComplete_name().equalsIgnoreCase("")
                || teacher.getAddress() == null || teacher.getAddress().equalsIgnoreCase("")
                || teacher.getCurp() == null || teacher.getCurp().equalsIgnoreCase("")
                || teacher.getEmail() == null || teacher.getEmail().equalsIgnoreCase("")
                || teacher.getRfc() == null || teacher.getRfc().equalsIgnoreCase("")
                || teacher.getPhone_number() == null || teacher.getPhone_number().equalsIgnoreCase("")) {
            flag = true;
        }

        return flag;
    }

    public void clearFrmNewTeacher() {
        teacher = new TeacherModel();
    }

    private void loadDataTeacher() {
        listTeachers = new ArrayList<>();
        listTeachers = teacherDAO.getListTeachers();
    }

    public void setAsingTeacher(TeacherModel teacher) {
        teacher_aux = teacher;
    }

    public void remTeacher() {
        if (teacherDAO.remTeacher(teacher_aux) != 0) {
            loadDataTeacher();
            viewMessageLaunch("El profesor ha sido eliminado.", "Exito! ", "Success");
        } else {
            viewMessageLaunch("Ocurrio un error al eliminar el profesor.", "Aviso! ", "Error");
        }
    }

    public void addTeacher() {
        String teacher_name = teacher.getComplete_name();
        if (validateInfoFrmTeacher(teacher)) {
            viewMessageLaunch("Asegurese de no dejar ningun campo sin información.", "Aviso! ", "Error");
        } else {
            if (validateTeacherExist(teacher)) {
                viewMessageLaunch("El correo, el rfc o el curp del profesor que esta intentando registrar ya existe,"
                        + " verifique que el profesor que esta intentando dar de alta no exista.", "Aviso! ", "Error");
            } else {
                if (teacherDAO.addTeacher(teacher) != 0) {
                    loadDataTeacher();
                    clearFrmNewTeacher();
                    viewMessageLaunch("El profesor " + teacher_name + " ha sido registrado.", "Exito! ", "Success");
                } else {
                    viewMessageLaunch("Ocurrio un error al registrar el profesor.", "Aviso! ", "Error");
                }
            }
        }
    }

    public void updTeacher() {
        String teacher_name = teacher_aux.getComplete_name();
        if (validateInfoFrmTeacher(teacher_aux)) {
            viewMessageLaunch("Asegurese de no dejar ningun campo sin información.", "Aviso! ", "Error");
        } else {
            if (teacherDAO.updTeacher(teacher_aux) != 0) {
                loadDataTeacher();
                clearFrmNewTeacher();
                viewMessageLaunch("El profesor " + teacher_name + " ha sido actualizado.", "Exito! ", "Success");
            } else {
                viewMessageLaunch("Ocurrio un error al actualizar el profesor.", "Aviso! ", "Error");

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

    public List<TeacherModel> getListTeachers() {
        return listTeachers;
    }

    public void setListTeachers(List<TeacherModel> listTeachers) {
        this.listTeachers = listTeachers;
    }

    public TeacherModel getTeacher_aux() {
        return teacher_aux;
    }

    public void setTeacher_aux(TeacherModel teacher_aux) {
        this.teacher_aux = teacher_aux;
    }

    public TeacherModel getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherModel teacher) {
        this.teacher = teacher;
    }

    public List<GradeGroupModel> getListGradeGroupByAssign() {
        return listGradeGroupByAssign;
    }

    public void setListGradeGroupByAssign(List<GradeGroupModel> listGradeGroupByAssign) {
        this.listGradeGroupByAssign = listGradeGroupByAssign;
    }

    public List<GradeGroupModel> getListAssignedGradeGroup() {
        return listAssignedGradeGroup;
    }

    public void setListAssignedGradeGroup(List<GradeGroupModel> listAssignedGradeGroup) {
        this.listAssignedGradeGroup = listAssignedGradeGroup;
    }

    public List<GradeGroupModel> getListSelectedGradeGroupByAssign() {
        return listSelectedGradeGroupByAssign;
    }

    public void setListSelectedGradeGroupByAssign(List<GradeGroupModel> listSelectedGradeGroupByAssign) {
        this.listSelectedGradeGroupByAssign = listSelectedGradeGroupByAssign;
    }

    public List<GradeGroupModel> getListSelectedGradeGroupByRemove() {
        return listSelectedGradeGroupByRemove;
    }

    public void setListSelectedGradeGroupByRemove(List<GradeGroupModel> listSelectedGradeGroupByRemove) {
        this.listSelectedGradeGroupByRemove = listSelectedGradeGroupByRemove;
    }

    public List<SubjectModel> getListAssignedSubject() {
        return listAssignedSubject;
    }

    public void setListAssignedSubject(List<SubjectModel> listAssignedSubject) {
        this.listAssignedSubject = listAssignedSubject;
    }

    public List<SubjectModel> getListSubjectByAssign() {
        return listSubjectByAssign;
    }

    public void setListSubjectByAssign(List<SubjectModel> listSubjectByAssign) {
        this.listSubjectByAssign = listSubjectByAssign;
    }
}
