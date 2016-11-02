/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
@WebServlet(urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
//        Connection connection = null;
//        SQLConnect msql = new SQLConnect();
//        Statement statement;
//        ResultSet rs;

//        try {

//            connection = msql.conectar();
//            statement = connection.createStatement();
//            rs = statement.executeQuery("SELECT codi, nom FROM alumne");
//
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet PruebaServlets</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet PruebaServlets at " + request.getContextPath() + "</h1>");
//            out.println("<UL>");
//
//            while (rs.next()) {
//                out.println("<LI>" + rs.getString("codi") + " " + rs.getString("nom"));
//            }
//
//            out.println("</UL>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {
//            out.close();
//        }
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

        try {

            Connection connection = null;
            SQLConnect msql = new SQLConnect();
            Gson gson = new Gson();
            JsonElement jsonElmnt;
            JsonArray jsonArr;

            ArrayList<Alumnos> alumn = new ArrayList<Alumnos>();

            connection = msql.conectar();

            alumn = msql.getListaAlumn(connection); //Codigo y nombre de los alumnos listados

            jsonElmnt = gson.toJsonTree(alumn);
            jsonArr = jsonElmnt.getAsJsonArray();
            
            
            response.setContentType("application/json");
            response.getWriter().print(jsonArr);

        } catch (SQLException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {

            String code = request.getParameter("code");
            Connection connection = null;
            SQLConnect msql = new SQLConnect();
            Gson gson = new Gson();
            String jsonElmnt;

            Alumnos alumn = new Alumnos();

            connection = msql.conectar();

            alumn = msql.getAssigTutorias(connection, Integer.parseInt(code));

            jsonElmnt = gson.toJson(alumn);

            response.setContentType("application/json");
            response.getWriter().print(jsonElmnt);

        } catch (SQLException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
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
