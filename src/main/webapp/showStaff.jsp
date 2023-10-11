<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/10/2023
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name Staff</th>
        <th scope="col">Email</th>
        <th scope="col">Address</th>
        <th scope="col">Phone Number</th>
        <th scope="col">Salary</th>
        <th scope="col">Department</th>
        <th scope="col">Action</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${staffList.size() != 0}">
    <c:forEach var="i" begin="0" end="${staffList.size()-1}">
    <tr>
        <td>${staffList.get(i).idStaff}</td>
        <td>${staffList.get(i).name}</td>
        <td>${staffList.get(i).email}</td>
        <td>${staffList.get(i).address}</td>
        <td>${staffList.get(i).phoneNumber}</td>
        <td>${staffList.get(i).salary}</td>
        <td>${departmentList.get(i).nameDepartment}</td>
        <td><a href="StaffServlet?action=editStaff&id=${staffList.get(i).idStaff}">Edit</a></td>
        <td><a href="StaffServlet?action=delete&idDelete=${staffList.get(i).idStaff}">delete</a></td>
    </tr>
    </c:forEach>
    </c:if>
    </tbody>
</table>
<h2><a href="StaffServlet?action=create">Create new Staff</a> </h2>
<h2><a href="StaffServlet?action=findByName">Find by name</a> </h2>
<h2><a href="StaffServlet?action=null">Home</a> </h2>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name Department</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${departmentAllList.size() != 0}">
        <c:forEach var="i" begin="0" end="${departmentAllList.size()-1}">
            <c:if test="${departmentAllList.get(i).status == 1}">
            <tr>
                <td>${departmentAllList.get(i).idDepartment}</td>
                <td>${departmentAllList.get(i).nameDepartment}</td>
                <td><a href="DepartmentServlet?action=editDepartment&id=${departmentAllList.get(i).idDepartment}">Edit</a></td>
                <td><a href="DepartmentServlet?action=deleteDepartment&idDelete=${departmentAllList.get(i).idDepartment}">Delete</a></td>
            </tr>
            </c:if>
        </c:forEach>
    </c:if>
    </tbody>
</table>
<h2><a href="DepartmentServlet?action=create">Create new department</a> </h2>
<h2><a href="DepartmentServlet?action=findByName">Find by name</a> </h2>
</body>
</html>

