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
                int status = rs.getInt("status");
                departmentList.add(new Department(idDepartment, nameDepartment, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentList;
    }

    @Override
    public void create(Department department) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into department(nameDepartment, status) values (?, ?)");) {
            preparedStatement.setString(1, department.getNameDepartment());
            preparedStatement.setInt(2, department.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(Department department) throws SQLException {
        boolean rowUpdate;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("update department set nameDepartment= ?where idDepartment = ?");
            statement.setString(1, department.getNameDepartment());
            statement.setInt(2, department.getIdDepartment());
            rowUpdate = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowUpdate;
    }

    @Override
    public boolean delete(int id){
        boolean rowDelete;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update department set status= 0 where idDepartment = ?");
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDelete;
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
                int status = rs.getInt("status");
                departmentList.add(new Department(idDepartment, name, status));
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
                int status = rs.getInt("status");
                department = new Department(id, name, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }


}
