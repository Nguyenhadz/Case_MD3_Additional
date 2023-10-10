package com.example.bai_thi_thuc_hanh_md03.service.department;

import com.example.bai_thi_thuc_hanh_md03.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService{
    protected Connection getConnection() {
        Connection connection = null;
        try {
            java.lang.Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thmd3?useSSL=false", "root", "123456");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public List<Department> findAll() {
        List<Department> departmentList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from department");){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idDepartment = rs.getInt("idDepartment");
                String nameDepartment = rs.getString("nameDepartment");
                departmentList.add(new Department(idDepartment, nameDepartment));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentList;
    }

    @Override
    public void create(Department department) throws SQLException {

    }

    @Override
    public boolean update(Department department) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public List<Department> findByName(String name) {
        List<Department> departmentList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from department where nameDepartment like ?");
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idDepartment = rs.getInt("idDepartment");
                departmentList.add(new Department(idDepartment, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentList;
    }

    @Override
    public Department findById(int id) {
        Department department = null;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from department where idDepartment =?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("nameDepartment");
                department = new Department(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }
}
