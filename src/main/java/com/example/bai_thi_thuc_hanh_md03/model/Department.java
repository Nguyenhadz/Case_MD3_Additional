package com.example.bai_thi_thuc_hanh_md03.model;

public class Department {
    private int idDepartment;
    private String nameDepartment;
    private int status;

    public Department() {
    }

    public Department(int idDepartment, String nameDepartment, int status) {
        this.idDepartment = idDepartment;
        this.nameDepartment = nameDepartment;
        this.status = status;
    }

    public Department(String nameDepartment, int status) {
        this.nameDepartment = nameDepartment;
        this.status = status;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
