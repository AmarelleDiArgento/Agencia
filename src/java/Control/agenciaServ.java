/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Implementar.AdminImp;
import Model.Tablas.agenciaTab;
import Servicios.Mensajes;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author freyd
 */
public class agenciaServ extends HttpServlet {

    @Resource(name = "Pool")
    private DataSource pool;

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
            throws ServletException, IOException {
        HttpSession Ses = request.getSession(true);
        //if (Ses.getAttribute("log") != null) {

        Mensajes m = new Mensajes();
        if (Ses.getAttribute("msj") != null) {
            m = (Mensajes) Ses.getAttribute("msj");
        }
        String ruta;

        if (Ses.getAttribute("jsp") != null) {
            ruta = (String) Ses.getAttribute("jsp");
        } else {
            ruta = "agencia.jsp";
        }

        //if (Ses.getAttribute("log") != null) {
        String Accion = request.getParameter("accion");

        agenciaTab a = null;

        int IdAgencia;
        String Nombre;
        String Telefono;
        String Direccion;

        try {
            AdminImp Asql = new AdminImp(pool);

            switch (Accion) {
                
                case "registrar":
                    Nombre = request.getParameter("nombre");
                    Telefono = request.getParameter("telefono");
                    Direccion = request.getParameter("direccion");
                    a = new agenciaTab(Nombre, Telefono, Direccion);
                    m = Asql.getAgencia().insertar(a);
                    break;

                case "Modificar":
                    IdAgencia = Integer.parseInt(request.getParameter("id"));
                    Nombre = request.getParameter("nombre");
                    Telefono = request.getParameter("telefono");
                    Direccion = request.getParameter("direccion");
                    a = new agenciaTab(Nombre, Telefono, Direccion);
                    m = Asql.getAgencia().modificar(a);
                    break;
                
                case "Eliminar":
                    IdAgencia = Integer.parseInt(request.getParameter("id"));
                    m = Asql.getAgencia().eliminar(IdAgencia);
                    break;
                
                case "Obtener":
                    
                        IdAgencia = Integer.parseInt(request.getParameter("id"));
                        a = Asql.getAgencia().obtener(IdAgencia);
                        Ses.setAttribute("age", a);
                        m.setMsj("Se ha obtenido el tipo con id: " + a.getId_agencia());
                        m.setTipo("Mod");

                    break;
                case "Listar":
                    
                    List<agenciaTab> agl = Asql.getAgencia().listar();
                    Ses.setAttribute("lisAge", agl);
                    //} else {
                    // msj = "No tienes permisos para consultar registros";
                    //}
                    break;

                default:
                    ruta = "agencia.jsp";
            }
        } catch (SQLException ex) {
            m.setTipo("Error");
            m.setMsj("MySql Error");
            m.setDetalles("Detalles: " + ex);

        } catch (Exception ex) {
            m.setTipo("Error");
            m.setMsj("Error");
            m.setDetalles("Detalles: " + ex);

        }
        //}else{
        //    ruta = "index.jsp";
        //    msj = "No has iniciado sesión";
        //}
        if (m.getTipo() != null) {
            Ses.setAttribute("msj", m);
        }
        request.getRequestDispatcher(ruta).forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
