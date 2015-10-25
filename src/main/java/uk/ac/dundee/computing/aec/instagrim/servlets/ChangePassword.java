/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.UserModel;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;

/**
 *
 * @author Kasia
 */
@WebServlet(name = "ChangePassword", urlPatterns = {"/ChangePassword"})
public class ChangePassword extends HttpServlet {
    Cluster cluster=null;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        UserModel us = new UserModel();
        us.setCluster(cluster);
        
        String newpass=request.getParameter("newpass");
        String renewpass=request.getParameter("renewpass");
        
        
        
        HttpSession session=request.getSession();
            LoggedIn lg= (LoggedIn)session.getAttribute("LoggedIn");
            String uname = "majed";
            if(lg != null){
                if (lg.getlogedin()){
                    if (!newpass.equals(renewpass)){
                        response.sendRedirect("/Instagrim/update");
                    }
                    else{
                        uname=lg.getUsername();
                        if(us.ChangePass(uname, newpass)){
                           response.sendRedirect("/Instagrim/index"); 
                        }
                        else{
                            response.sendRedirect("/Instagrim/update");
                        }
                
                    }
                }
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
