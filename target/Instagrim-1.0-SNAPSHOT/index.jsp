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
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%@include file="nav.jsp" %>
        <div id="center">
            <h1>
                Black, White and All Shades of Grey!
            </h1>
            <h2>
                Feels like Aberdeen.
            </h2>
                <%   
                LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                if (lg != null) {
                    String UserName = lg.getUsername();
                    if (lg.getlogedin()) {
                %>
            <h3>
                You're logged in as <i><%=UserName%></i>.
            </h3>
                <%
                    }
                }
                %>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
