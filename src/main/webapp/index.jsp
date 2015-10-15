<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script> 
            $(function(){
              $("#header").load("header.html");
              $("#footer").load("footer.html");
            });
        </script>
    </head>
    <body>
        <header id="header"></header>
        <nav>
            <ul>

               
                    <%
                        
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null) {
                            String UserName = lg.getUsername();
                            if (lg.getlogedin()) {
                    %>
                <li><a href="upload">Upload</a></li>
                <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                <li><a href="/Instagrim/Logout">Log Out</a></li>
                    <%}
                            }else{
                                %>
                 <li><a href="register">Register</a></li>
                <li><a href="login">Login</a></li>
                <%
                                        
                            
                    }%>
            </ul>
        </nav>
        <footer id="footer"></footer>
    </body>
</html>
