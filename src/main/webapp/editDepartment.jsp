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
<%--    <script type="text/javascript" src="checkInput.js" ></script>--%>
<%--    <style>--%>
<%--        .error {--%>
<%--            color: red;--%>
<%--        }--%>
<%--    </style>--%>
</head>
<body>
<form name="myForm" method="post" onsubmit="return validateForm()">
    <table style="width: 500px; height: 100px" class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name Department</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${department.idDepartment}</td>
            <td><input type="text" name="name" value="${department.nameDepartment}" required/></td>
            <td><input type="hidden" name="status" value="${department.status}"/></td>
            <td><input type="submit" value="Save"></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
