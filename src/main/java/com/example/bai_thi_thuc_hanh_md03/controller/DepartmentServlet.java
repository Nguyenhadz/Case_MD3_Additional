package com.example.bai_thi_thuc_hanh_md03.controller;

import com.example.bai_thi_thuc_hanh_md03.model.Department;
import com.example.bai_thi_thuc_hanh_md03.model.Staff;
import com.example.bai_thi_thuc_hanh_md03.service.department.DepartmentServiceImpl;
import com.example.bai_thi_thuc_hanh_md03.service.staff.StaffServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "departmentServlet", value = "/DepartmentServlet")
public class DepartmentServlet extends HttpServlet {
    DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
    StaffServiceImpl staffService = new StaffServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "editDepartment":
                    showEditDepartment(request, response);
                    break;
                case "deleteDepartment":
                    deleteDepartment(request, response);
                    break;
                case "create":
                    showCreateDepartment(request, response);
                    break;
                case "findByName":
                    showFindByName(request, response);
                    break;
                case "restoreDepartment":
                    restoreDepartment(request, response);
                    break;
                default:
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void restoreDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        departmentService.restore(id);
        response.sendRedirect("StaffServlet?action=null");
    }

    private void showFindByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("searchDepartment.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showCreateDepartment(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("createDepartment.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("idDelete"));
        departmentService.delete(id);
        response.sendRedirect("StaffServlet?action=null");
    }

    private void showEditDepartment(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Department department = departmentService.findById(id);
        RequestDispatcher dispatcher;
        request.setAttribute("department", department);
        dispatcher = request.getRequestDispatcher("editDepartment.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "editDepartment":
                    editDepartment(request, response);
                    break;
                case "create":
                    createDepartment(request, response);
                    break;
                case "findByName":
                    findDepartment(request, response);
                    break;
                default:
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void findDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchDepartment = request.getParameter("searchDepartment");
        List<Staff> staffList = staffService.findAll();
        List<Department> departmentList = findAllDepartment(staffList);
        List<Department> departmentAllList = departmentService.findAllToSearch(searchDepartment);
        request.setAttribute("departmentAllList", departmentAllList);
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

    private void createDepartment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        int status = Integer.parseInt(request.getParameter("status"));
        departmentService.create(new Department(name, status));
        response.sendRedirect("StaffServlet?action=nul");
    }

    private void editDepartment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int status = Integer.parseInt(request.getParameter("status"));
        Department department = new Department(id, name, status);
        departmentService.update(department);
        response.sendRedirect("StaffServlet?action=null");

    }

}