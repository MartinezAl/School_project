package com.school.system.controller;

import com.school.model.TuitionModel;
import com.school.system.dao.TuitionDAO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "tuition_school")
@ViewScoped
public class TuitionController {

    private List<TuitionModel> listTuitions;
    private TuitionDAO tuitionDAO;

    @PostConstruct
    public void initLoadData() {
        tuitionDAO = new TuitionDAO();
        listTuitions = new ArrayList<>();
        listTuitions = tuitionDAO.getListTuitions();
    }

    public List<TuitionModel> getListTuitions() {
        return listTuitions;
    }

    public void setListTuitions(List<TuitionModel> listTuitions) {
        this.listTuitions = listTuitions;
    }
}
