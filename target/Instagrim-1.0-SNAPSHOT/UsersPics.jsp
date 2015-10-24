<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
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
            <h1>Picture Collection</h1>
        <%
            java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
            if (lsPics == null) {
        %>
        <p>No Pictures found</p>
        <%
        } else {
            Iterator<Pic> iterator;
            iterator = lsPics.iterator();
            while (iterator.hasNext()) {
                Pic p = (Pic) iterator.next();

        %>
        <a href="/Instagrim/Image/<%=p.getSUUID()%>" ><img src="/Instagrim/Thumb/<%=p.getSUUID()%>"></a>
        <%
                        
                    LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                    
                    if (lg != null) {
                        String uname = lg.getUsername();
                        if (lg.getlogedin() && uname.equals(p.getUser())) {
            %>
        <a href="/Instagrim/RmImage/<%=p.getSUUID()%>" >Delete</a>&nbsp&nbsp&nbsp
        <a href="/Instagrim/SetPic/<%=p.getSUUID()%>" >Set as Profile Pic</a><br/>
        <%
                        }
                    }
                    %><br/><%
            }
            }
        %>
        </article>
        <%@include file="footer.jsp" %>
    </body>
</html>
