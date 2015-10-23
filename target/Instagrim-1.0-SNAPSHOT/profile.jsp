<%-- 
    Document   : profile
    Created on : 2015-10-20, 18:39:52
    Author     : Kasia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%@include file="nav.jsp" %>
        <article>
        <%
            UserInfo usInf = (UserInfo) request.getAttribute("Info");
            if (usInf == null) {
        %>
        <h1>No Profile Found</h1>
        <h3><a href="/Instagrim/search">Search for other profiles</a></h3>
        <%
        } else {

        %>
        <h1><%=usInf.getUname()%>'s Profile </h1>
        <img src="/Instagrim/Thumb/<%=usInf.getProfPic()%>">
        <ul>
            <li><b>Name:</b> <%=usInf.getFname()%> <%=usInf.getLname()%></li>
            <li><b>Address:</b> <%=usInf.getStreet()%>, <%=usInf.getCity()%>, <%=usInf.getZip()%></li>
            <li><b>Email:</b> <%=usInf.getEmail()%> </li>
            <li><a href="/Instagrim/Images/<%=usInf.getUname()%>"><%=usInf.getUname()%>'s Pictures</a></li>
        </ul>
        <%
            }   
        %>
        </article>
        <%@include file="footer.jsp" %>
    </body>
</html>
