/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.UserModel;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {
    Cluster cluster=null;
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }




    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String repassword=request.getParameter("repassword");
        
        String firstname=request.getParameter("firstname");
        String lastname=request.getParameter("lastname");
        String street=request.getParameter("street");
        String city=request.getParameter("city");
        String zip=request.getParameter("zip");
        String email=request.getParameter("email");
        
        int numzip;
        try{
            numzip = Integer.parseInt(zip);
        }
        catch (Exception e){
            //some alert maybe
            response.sendRedirect("register");
            return;
        }     
        if(!username.equals("")){
            if (password.equals(repassword))
            {
                UserModel us=new UserModel();
                us.setCluster(cluster);
                us.RegisterUser(username, password, firstname, lastname, street, city, numzip, email);
                response.sendRedirect("/Instagrim");
            }
            else
            {
                //some alert maybe
                response.sendRedirect("register");
            }
        }
        else{
            //some alert maybe
            response.sendRedirect("register");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
