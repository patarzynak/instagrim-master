<%-- 
    Document   : nav.jsp
    Created on : 2015-10-15, 21:42:27
    Author     : Kasia
--%>

<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <nav>
        <ul>

               
                    <%
                        
                        LoggedIn lgn = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lgn != null) {
                            String UserName = lgn.getUsername();
                            if (lgn.getlogedin()) {
                    %>
                <li><a href="/Instagrim/upload">Upload</a></li>
                <li><a href="/Instagrim/Images/<%=lgn.getUsername()%>">Your Images</a></li>
                <li><a href="/Instagrim/Profile/<%=lgn.getUsername()%>">Your Profile</a></li>
                <li><a href="/Instagrim/Logout">Log Out</a></li>
                    <%}
                            }else{
                                %>
                <li><a href="/Instagrim/login">Login</a></li>
                <li><a href="/Instagrim/register">Register</a></li>
                <li><a href="/Instagrim/Images/majed">Sample Images</a></li>
                <%
                                        
                            
                    }%>
            </ul>
    </nav>
</html>
