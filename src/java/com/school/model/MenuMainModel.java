package com.school.model;

import java.io.Serializable;

public class MenuMainModel implements Serializable {

    private String opc_title_name;
    private String opc_desc_mod;
    private String opc_image;

    public MenuMainModel() {
    }

    public MenuMainModel(String opc_title_name, String opc_desc_mod, String opc_image) {
        this.opc_title_name = opc_title_name;
        this.opc_desc_mod = opc_desc_mod;
        this.opc_image = opc_image;
    }

    public String getOpc_title_name() {
        return opc_title_name;
    }

    public void setOpc_title_name(String opc_title_name) {
        this.opc_title_name = opc_title_name;
    }

    public String getOpc_desc_mod() {
        return opc_desc_mod;
    }

    public void setOpc_desc_mod(String opc_desc_mod) {
        this.opc_desc_mod = opc_desc_mod;
    }

    public String getOpc_image() {
        return opc_image;
    }

    public void setOpc_image(String opc_image) {
        this.opc_image = opc_image;
    }

    @Override
    public String toString() {
        return "MenuMainModel{" + "opc_title_name=" + opc_title_name + ", opc_desc_mod=" + opc_desc_mod + ", opc_image=" + opc_image + '}';
    }
}
