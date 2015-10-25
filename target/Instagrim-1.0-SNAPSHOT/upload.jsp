<%-- 
    Document   : upload
    Created on : Sep 22, 2014, 6:31:50 PM
    Author     : Administrator
--%>

<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn"%>
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
            <%    
            LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
            if (lg != null) {
                if (lg.getlogedin()) {
            %>
        <article>
            <h3>File Upload</h3>
            <form method="POST" enctype="multipart/form-data" action="Image">
                File to upload: <input type="file" name="upfile"><br/>
                Title: <input type="text" name="title"><br/>
                <br/>
                <input type="submit" value="Press"> to upload the file!
            </form>
        </article>

            <%  }
                    }
                    else{
            %>
        <article>
            <h3>You have to be logged in to upload photos.</h3>
        </article>
            <%
            }
            %>
     
            <%@include file="footer.jsp" %>
    </body>
</html>
