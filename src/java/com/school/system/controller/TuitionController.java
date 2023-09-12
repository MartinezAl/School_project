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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "tuition_school")
@ViewScoped
public class TuitionController implements Serializable {

    private List<TuitionModel> listTuitions;
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

    private String formatterDate(Date date) {
        String formDate = "";
        DateFormat formatter = new SimpleDateFormat("MM/yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("CST6CDT"));
        formDate = formatter.format(date);
        TimeZone.getAvailableIDs();
        return formDate;
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
}
