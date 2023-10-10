package com.example.bai_thi_thuc_hanh_md03.model;

public class Staff {
    private int idStaff;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private double salary;
    private int idDepartment;

    public Staff() {
    }

    public Staff(int idStaff, String name, String email, String address, String phoneNumber, double salary, int idDepartment) {
        this.idStaff = idStaff;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.idDepartment = idDepartment;
    }

    public Staff(String name, String email, String address, String phoneNumber, double salary, int idDepartment) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.idDepartment = idDepartment;
    }

    public int getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }
}
