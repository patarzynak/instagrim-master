<%-- 
    Document   : footer
    Created on : 2015-10-15, 13:54:52
    Author     : Kasia
--%>

<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <footer>
        <ul>
            <li class="footer"><a href="/Instagrim">Home</a></li>
            <%
                        
                    LoggedIn lgf = (LoggedIn) session.getAttribute("LoggedIn");
                    if (lgf != null) {
                        if (lgf.getlogedin()) {
            %>
            <li><a href="/Instagrim/Logout">Log Out</a></li>
            <%
                        }
                    }
            %>
            <li>&COPY; KP</li>
        </ul>
    </footer>
</html>
