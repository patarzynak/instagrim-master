<%-- 
    Document   : register.jsp
    Created on : Sep 28, 2014, 6:29:51 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%@include file="nav.jsp" %>
       
        <article>
            <h3>Register as user</h3>
            <form method="POST"  action="Register">
                <ul>
                    <li>User Name <input type="text" name="username"></li>
                    <li>Password <input type="password" name="password"></li>
                    <li>Repeat Password <input type="password" name="repassword"></li>
                    
                    <li>First Name <input type="text" name="firstname"></li>
                    <li>Last Name <input type="text" name="lastname"></li>
                    <li><b>Address: </b></li>
                    <li>Street <input type="text" name="street"></li>
                    <li>City <input type="text" name="city"></li>
                    <li>ZIP code <input type="text" name="zip"></li>
                    <li><b>Email </b><input type="text" name="email"></li>
                </ul>
                <br/>
                <input type="submit" value="Register"> 
            </form>

        </article>
        <%@include file="footer.jsp" %>
    </body>
</html>
