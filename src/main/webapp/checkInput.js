function validateForm() {
    let regexName = /^[A-Za-z\s]+$/;
    let regexEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    let regexPhone = /^0\d{9}$/;
    let regexSalary = /^(?!0)\d*\.?0?$/;
    let username = document.forms["myForm"]["name"].value;
    let email = document.forms["myForm"]["email"].value;
    let phone = document.forms["myForm"]["phoneNumber"].value;
    let salary = document.forms["myForm"]["salary"].value;
    let isValid = true;
    if (!regexName.test(username)) {
        document.getElementById("usernameError").innerHTML = "Tên không hợp lệ";
        isValid = false;
    } else {
        document.getElementById("usernameError").innerHTML = "";
    }
    if (!regexEmail.test(email)) {
        document.getElementById("emailError").innerHTML = "Email không hợp lệ";
        isValid = false;
    } else {
        document.getElementById("emailError").innerHTML = "";
    }
    if (!regexPhone.test(phone)) {
        document.getElementById("phoneError").innerHTML = "Sai số điện thoại";
        isValid = false;
    } else {
        document.getElementById("phoneError").innerHTML = "";
    }
    if (!regexSalary.test(salary)) {
        document.getElementById("salaryError").innerHTML = "Nhập lại lương";
        isValid = false;
    } else {
        document.getElementById("salaryError").innerHTML = "";
    }
    return isValid;
}