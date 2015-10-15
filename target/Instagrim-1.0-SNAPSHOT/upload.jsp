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
        
            <%    
            LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
            if (lg != null) {
                if (lg.getlogedin()) {
            %>
        <nav>
            <ul>
                <li class="nav"><a href="upload">Upload</a></li>
                <li class="nav"><a href="/Instagrim/Images/majed">Sample Images</a></li>
            </ul>
        </nav>
        <article>
            <h3>File Upload</h3>
            <form method="POST" enctype="multipart/form-data" action="Image">
                File to upload: <input type="file" name="upfile"><br/>

                <br/>
                <input type="submit" value="Press"> to upload the file!
            </form>
        </article>
            <%  }
                    }
                    else{
            %>
        <nav>
            <ul>
                <li class="nav"><a href="login">Login</a></li>
                <li class="nav"><a href="register">Register</a></li>
            </ul>
        </nav>
        <article>
            <h3>You have to be logged in to upload photos.</h3>
        </article>
            <%
            }
            %>
            <footer id="footer"></footer>
    </body>
</html>
