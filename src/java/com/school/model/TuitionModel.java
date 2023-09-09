package com.school.model;

import java.io.Serializable;

public class TuitionModel implements Serializable {

    private int id_tituition;
    private Double amount;
    private String period_init;
    private String period_final;
    private boolean flag_active;
    private String to_periods;

    public TuitionModel() {
    }

    public TuitionModel(int id_tituition, Double amount, String period_init, String period_final, boolean flag_active) {
        this.id_tituition = id_tituition;
        this.amount = amount;
        this.period_init = period_init;
        this.period_final = period_final;
        this.flag_active = flag_active;
    }

    public int getId_tituition() {
        return id_tituition;
    }

    public void setId_tituition(int id_tituition) {
        this.id_tituition = id_tituition;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPeriod_init() {
        return period_init;
    }

    public void setPeriod_init(String period_init) {
        this.period_init = period_init;
    }

    public String getPeriod_final() {
        return period_final;
    }

    public void setPeriod_final(String period_final) {
        this.period_final = period_final;
    }

    public boolean isFlag_active() {
        return flag_active;
    }

    public void setFlag_active(boolean flag_active) {
        this.flag_active = flag_active;
    }

    public String getTo_periods() {
        return period_init + " - " + period_final;
    }

    @Override
    public String toString() {
        return "TuitionModel{" + "id_tituition=" + id_tituition + ", amount=" + amount + ", period_init=" + period_init + ", period_final=" + period_final + ", flag_active=" + flag_active + '}';
    }

}
