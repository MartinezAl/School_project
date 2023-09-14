package com.school.system.controller;

import com.school.model.TuitionModel;
import com.school.system.dao.TuitionDAO;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "tuition_school")
@ViewScoped
public class TuitionController implements Serializable {

    private List<TuitionModel> listTuitions;
    private TuitionModel v_tuition;
    private TuitionDAO tuitionDAO;
    private TuitionModel tuition;
    private Date period_final;
    private Date period_init;

    @PostConstruct
    public void initLoadData() {
        tuition = new TuitionModel();
        tuitionDAO = new TuitionDAO();
        listTuitions = new ArrayList<>();
        listTuitions = tuitionDAO.getListTuitions();
    }

    public void removeTuition() {
        if (tuitionDAO.removePeriodTuition(v_tuition) != 0) {
            initLoadData();
            viewMessageLaunch("El periodo " + v_tuition.getTo_periods() + ", ha sido eliminado de manera correcta.",
                    "Exito! ", "Success");
        } else {
            viewMessageLaunch("Ocurrio un error en el registro del periodo.",
                    "Error! ", "Error");
        }
    }

    public void closePeriodTuition() {
        if (tuitionDAO.closePeriodTuition(v_tuition) != 0) {
            initLoadData();
            viewMessageLaunch("El periodo " + v_tuition.getTo_periods() + ", ha sido cerrado.",
                    "Exito! ", "Success");
        } else {
            viewMessageLaunch("Ocurrio un error en el registro del periodo.",
                    "Error! ", "Error");
        }
    }

    public void addTuition() {
        if (validateEmptyFormAddTuition()) {
            viewMessageLaunch("Asegurese de poner la información requerida para el registro del periodo.",
                    "Error! ", "Error");
        } else {
            tuition.setPeriod_init(formatterDate(period_init));
            tuition.setPeriod_final(formatterDate(period_final));
            if (validateExistPeriod(tuition)) {
                viewMessageLaunch("El periodo ya existe, debe cerrar el "
                        + "periodo y eliminarlo en ese orden o intente abrir otro periodo nuevo.",
                        "Error! ", "Error");
            } else {
                if (tuitionDAO.addTuition(tuition) != 0) {
                    period_final = null;
                    period_init = null;
                    initLoadData();
                    viewMessageLaunch("Se ha abierto un nuevo periodo.", "Exito! ", "Success");
                } else {
                    viewMessageLaunch("Ocurrio un error en el registro del periodo.",
                            "Error! ", "Error");
                }
            }
        }
    }

    public void asingTuition(TuitionModel t) {
        v_tuition = t;
    }

    private boolean validateEmptyFormAddTuition() {
        boolean flag = false;
        if (period_init == null || period_final == null || tuition.getAmount() == 0.0) {
            flag = true;
        }

        return flag;
    }

    private boolean validateExistPeriod(TuitionModel tuition) {
        boolean flag = false;
        for (TuitionModel t : listTuitions) {
            if (t.getPeriod_init().equalsIgnoreCase(tuition.getPeriod_init())
                    && t.getPeriod_final().equalsIgnoreCase(tuition.getPeriod_final())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    private String formatterDate(Date date) {
        String formDate = "";
        DateFormat formatter = new SimpleDateFormat("MM/yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("CST6CDT"));
        formDate = formatter.format(date);
        TimeZone.getAvailableIDs();
        return formDate;
    }

    private void viewMessageLaunch(String mess, String messClass, String verifyMess) {
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(
                        (verifyMess.equalsIgnoreCase("Success") ? FacesMessage.SEVERITY_INFO
                        : verifyMess.equalsIgnoreCase("Warning") ? FacesMessage.SEVERITY_WARN
                        : verifyMess.equalsIgnoreCase("Error") ? FacesMessage.SEVERITY_ERROR : FacesMessage.SEVERITY_INFO),
                        messClass, mess));
    }

    public List<TuitionModel> getListTuitions() {
        return listTuitions;
    }

    public void setListTuitions(List<TuitionModel> listTuitions) {
        this.listTuitions = listTuitions;
    }

    public Date getPeriod_final() {
        return period_final;
    }

    public void setPeriod_final(Date period_final) {
        this.period_final = period_final;
    }

    public Date getPeriod_init() {
        return period_init;
    }

    public void setPeriod_init(Date period_init) {
        this.period_init = period_init;
    }

    public TuitionModel getTuition() {
        return tuition;
    }

    public void setTuition(TuitionModel tuition) {
        this.tuition = tuition;
    }

    public TuitionModel getV_tuition() {
        return v_tuition;
    }

    public void setV_tuition(TuitionModel v_tuition) {
        this.v_tuition = v_tuition;
    }
}
