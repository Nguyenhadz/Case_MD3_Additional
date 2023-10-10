package com.example.bai_thi_thuc_hanh_md03.service.staff;

import com.example.bai_thi_thuc_hanh_md03.model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class StaffServiceImpl implements StaffService{
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
    public List<Staff> findAll() {
        List<Staff> staffList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from staff");){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idStaff = rs.getInt("idStaff");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                double salary = rs.getDouble("salary");
                int idDepartment = rs.getInt("idDepartment");
                staffList.add(new Staff(idStaff, name, email, address, phoneNumber, salary, idDepartment));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    @Override
    public void create(Staff staff) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into staff(name, email, address, phoneNumber, salary, idDepartment) values (?, ?, ?, ?, ?, ?)");) {
            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, staff.getEmail());
            preparedStatement.setString(3, staff.getAddress());
            preparedStatement.setString(4, staff.getPhoneNumber());
            preparedStatement.setDouble(5, staff.getSalary());
            preparedStatement.setInt(6, staff.getIdDepartment());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(Staff staff) throws SQLException {
        boolean rowUpdate;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("update staff set name= ?, email =?, address=?, phoneNumber=?, salary=? , idDepartment=? where idStaff = ?");

            statement.setString(1, staff.getName());
            statement.setString(2, staff.getEmail());
            statement.setString(3, staff.getAddress());
            statement.setString(4, staff.getPhoneNumber());
            statement.setDouble(5, staff.getSalary());
            statement.setInt(6, staff.getIdDepartment());
            statement.setInt(7, staff.getIdStaff());
            rowUpdate = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowUpdate;
    }

    @Override
    public  boolean delete(int id) throws SQLException {
        boolean rowDelete;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from staff where idStaff = ?");
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDelete;
    }

    @Override
    public List<Staff> findByName(String name) {
        return null;
    }

    @Override
    public Staff findById(int id) {
        Staff staff = null;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from staff where idStaff = ?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                double salary = rs.getDouble("salary");
                int idDepartment = rs.getInt("idDepartment");
                staff = new Staff(id, name, email, address, phoneNumber, salary, idDepartment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staff;
    }

    public List<Staff> findAllToSearch(String searchStaff) {
        List<Staff> staffList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from staff where name like ?");
            preparedStatement.setString(1, "%" + searchStaff + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idStaff = rs.getInt("idStaff");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                double salary = rs.getDouble("salary");
                int idDepartment = rs.getInt("idDepartment");
                staffList.add(new Staff(idStaff, name, email, address, phoneNumber, salary, idDepartment));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }
}
