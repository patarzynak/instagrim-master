<%-- 
    Document   : updateProf
    Created on : 2015-10-24, 00:18:23
    Author     : Kasia
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
            <form method="POST"  action="Update">
                <ul>                    
                    <li>First Name <input type="text" name="firstname"></li>
                    <li>Last Name <input type="text" name="lastname"></li>
                </ul>
                <br/>
                <input type="submit" value="Update"> 
            </form>

        </article>
        <%@include file="footer.jsp" %>
    </body>
</html>
