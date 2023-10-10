package com.example.bai_thi_thuc_hanh_md03.controller;

import com.example.bai_thi_thuc_hanh_md03.model.Department;
import com.example.bai_thi_thuc_hanh_md03.model.Staff;
import com.example.bai_thi_thuc_hanh_md03.service.department.DepartmentServiceImpl;
import com.example.bai_thi_thuc_hanh_md03.service.staff.StaffService;
import com.example.bai_thi_thuc_hanh_md03.service.staff.StaffServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "staffServlet", value = "/StaffServlet")
public class StaffServlet extends HttpServlet {
    StaffServiceImpl staffService = new StaffServiceImpl();
    DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delete":
                try {
                    deleteStaff(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "editStaff":
                showEditStaff(request, response);
                break;
            case "create":
                createStaff(request, response);
                break;
            case "findByName":
                showFindByName(request, response);
                break;
            default:
                showListStaff(request, response);
                break;
        }

    }

    private void showFindByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("search.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createStaff(HttpServletRequest request, HttpServletResponse response) {
        List<Department> departmentList = new ArrayList<>();
        departmentList = departmentService.findAll();
        request.setAttribute("departmentList", departmentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("createStaff.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteStaff(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        int id = Integer.parseInt(request.getParameter("idDelete"));
        staffService.delete(id);
        showListStaff(request, response);
    }

    private void showEditStaff(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Staff staff = staffService.findById(id);
        List<Department> departmentList = departmentService.findAll();
        RequestDispatcher dispatcher;
        request.setAttribute("departmentList", departmentList);
        request.setAttribute("staff", staff);
        dispatcher = request.getRequestDispatcher("editStaff.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showListStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Staff> staffList = staffService.findAll();
        List<Department> departmentList = findAllDepartment(staffList);
        request.setAttribute("staffList", staffList);
        request.setAttribute("departmentList", departmentList);
        request.getRequestDispatcher("showStaff.jsp").forward(request, response);
    }

    private List<Department> findAllDepartment(List<Staff> staffList) {
        List<Department> departmentList = new ArrayList<>();
        for (Staff staff : staffList) {
            Department department = departmentService.findById(staff.getIdDepartment());
            departmentList.add(department);
        }
        return departmentList;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "editStaff":
                try {
                    editStaff(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "create":
                try {
                    saveStaff(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "findByName":
                findStaff(request, response);
                break;
            default:
        }

    }

    private void findStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchStaff = request.getParameter("searchStaff");
        List<Staff> staffList = staffService.findAllToSearch(searchStaff);
        List<Department> departmentList = findAllDepartment(staffList);
        request.setAttribute("staffList", staffList);
        request.setAttribute("departmentList", departmentList);
        request.getRequestDispatcher("showStaff.jsp").forward(request, response);
    }

    private void saveStaff(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        double salary = Double.parseDouble(request.getParameter("salary"));
        int idDepartment = Integer.parseInt(request.getParameter("idDepartment"));


        staffService.create(new Staff(name, email, address, phoneNumber, salary, idDepartment));
        showListStaff(request, response);
    }


    private void editStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        double salary = Double.parseDouble(request.getParameter("salary"));
        int idDepartment = Integer.parseInt(request.getParameter("idDepartment"));
        Staff staff = new Staff(id, name, email, address, phoneNumber, salary, idDepartment);
        staffService.update(staff);
        showListStaff(request, response);
    }
}