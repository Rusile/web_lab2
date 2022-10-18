<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <title>Login Tab</title>
</head>
<body>
<div align="center">
  <h1>Login Form</h1>
  <form id="login_form">
    <table style="with: 100%">
      <tr>
        <td>Login</td>
        <td><input type="text" name="login" id="login" minlength="2" /></td>
      </tr>
      <tr>
        <td>Password</td>
        <td><input type="password" name="password" id="password" minlength="2"/></td>
      </tr>

    </table>

    <input type="button" value="Login" onclick="loginUser()"/>
    <input type="button" value="Register" onclick="registerUser()"/>
  </form>
</div>
<div align="center">
  <p style="color: green; font-size: 0.7em" id="success"></p>
  <a style=" font-size: 0.7em" id="link"></a>
</div>

<script>
  function registerUser() {
    let password = document.getElementById("password").value;
    let login = document.getElementById("login").value;
    $.ajax({
      type: "POST",
      url: "./register",
      dataType: "html",
      data: "login=" + login +
              "&password=" + password,
      error: function (xhr, status, error) {
        alert("Login exists or login and password are wrong!");
      },
      success: function (data) {
        $("#success").html("Authorization successful!");
        $("#link").html("Let's start!");
        let link = document.getElementById('link');
        link.setAttribute('href', window.location.href);
      }
    });
  }
</script>

<script>
  function loginUser() {
    let password = document.getElementById("password").value;
    let login = document.getElementById("login").value;
    $.ajax({
      type: "POST",
      url: "./login",
      dataType: "html",
      data: "login=" + login +
              "&password=" + password,
      error: function (xhr, status, error) {
        alert("Login or password are wrong!");
      },
      success: function (data) {
        $("#success").html("Authorization successful!");
        $("#link").html("Let's start!");
        let link = document.getElementById('link');
        link.setAttribute('href', window.location.href);
      }

    });
  }
</script>

</body>
</html>
