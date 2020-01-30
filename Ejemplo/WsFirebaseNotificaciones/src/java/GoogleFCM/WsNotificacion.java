/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoogleFCM;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author josed
 */
@WebServlet(name = "WsNotificacion", urlPatterns = {"/WsNotificacion"})
public class WsNotificacion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   public boolean resultado =false;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //para que reconozca la ñ y los acentos
            request.setCharacterEncoding("UTF-8");
            String accion = request.getParameter("accion");
            String token = request.getParameter("token");
            try{
            FirebaseMessagingSnippets fr = new FirebaseMessagingSnippets();
        switch(accion)
        {
            case "men1":
                resultado = true;
                break;
                   case "men2":
                //fr.pushFCMNotification(token);resultado = true;
                break;
        }
          
          out.print(true);
            }catch(Exception ex){
                 out.print(false);
                System.err.println("LO SENTIMOS ESTE ES SU ERROR"+ex.getMessage());
            }
            
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //para que reconozca la ñ y los acentos
            request.setCharacterEncoding("UTF-8");
            String accion = request.getParameter("accion");
            String token = request.getParameter("token");
            try{
            FirebaseMessagingSnippets fr = new FirebaseMessagingSnippets();
        switch(accion)
        {
            case "men1":
                fr.send_FCM_Notification(token);resultado = true;
                break;
                   case "men2":
                break;
        }
          
          out.print(true);
            }catch(Exception ex){
                 out.print(false);
                System.err.println("LO SENTIMOS ESTE ES SU ERROR"+ex.getMessage());
            }
            
        }
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //para que reconozca la ñ y los acentos
            request.setCharacterEncoding("UTF-8");
            String accion = request.getParameter("accion");
            String token = request.getParameter("token");
            try{
            FirebaseMessagingSnippets fr = new FirebaseMessagingSnippets();
        switch(accion)
        {
            case "men1":
              //  fr.estasies(token);resultado = true;
                break;
                   case "men2":
              //  fr.pushFCMNotification(token);resultado = true;
                break;
        }
          
          out.print(true);
            }catch(Exception ex){
                 out.print(false);
                System.err.println("LO SENTIMOS ESTE ES SU ERROR"+ex.getMessage());
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
