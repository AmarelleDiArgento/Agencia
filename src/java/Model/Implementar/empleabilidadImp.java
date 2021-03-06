/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Implementar;

import Model.Interfaces.empleabilidad;
import Model.Tablas.empleabilidadTab;
import Servicios.Mensajes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author freyd
 */
public class empleabilidadImp implements empleabilidad {

    private final Connection con;
    Mensajes m = new Mensajes();

    public empleabilidadImp(Connection con) {

        this.con = con;
    }

    final String Insertar = "call Agencia.InsEmplea(?,?,?)";
    final String Modificar = "";
    final String Eliminar = "";
    final String Consultar = "";
    final String ListarTodos = "call Agencia.VerEmplea();";

    @Override
    public Mensajes insertar(empleabilidadTab e) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = con.prepareStatement(Insertar);
            stat.setString(1, e.getId_aspirante());
            stat.setInt(2, e.getId_oferta());
            stat.setString(3, e.getFecha());
            if (stat.executeUpdate() == 0) {
                m.setTipo("Error");
                m.setMsj("Error Mysql");
                m.setDetalles("Error al ingresar los datos");
            } else {
                m.setTipo("Ok");
                m.setMsj("Se a aplicado exitosamente a la oferta: " + e.getId_oferta());
            }

        } catch (SQLException ex) {
            m.setTipo("Error");
            m.setMsj("Error Mysql" + ex.getMessage());
            m.setDetalles("Error al ingresar los datos:" + ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    m.setTipo("Error");
                    m.setMsj("Error Mysql" + ex.getMessage());
                    m.setDetalles("Error al ingresar los datos:" + ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    m.setTipo("Error");
                    m.setMsj("Error Mysql Statement");
                    m.setDetalles("Error Statement, ingresar los datos:" + ex.getMessage());
                }
            }
        }
        return m;
    }

    @Override
    public Mensajes modificar(empleabilidadTab e) {

        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Modificar);
            stat.setString(1, e.getId_aspirante());
            stat.setInt(2, e.getId_oferta());
            stat.setString(3, e.getFecha());
            if (stat.executeUpdate() == 0) {

                m.setTipo("Error");
                m.setMsj("Error Mysql");
                m.setDetalles("Error al modificar los datos");
            } else {
                m.setTipo("Ok");
                m.setMsj(e.getAspirante() + " modificado exitosamente");
            }

        } catch (SQLException ex) {
            m.setTipo("Error");
            m.setMsj("Error Mysql" + ex.getMessage());
            m.setDetalles("Error al ingresar los datos:" + ex);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
            m.setTipo("Error");
            m.setMsj("Error Mysql" + ex.getMessage());
            m.setDetalles("Error al ingresar los datos:" + ex);
                }
            }
        }
        return m;
        
    }

    @Override
    public Mensajes eliminar(Integer id) {

        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Eliminar);
            stat.setInt(1, id);
            if (stat.executeUpdate() == 0) {
                m.setTipo("Error");
                m.setMsj("Error Mysql");
                m.setDetalles("Error al eliminar los datos");
            } else {
                m.setTipo("Ok");
                m.setMsj(id + " eliminado exitosamente");
            }

        } catch (SQLException ex) {
            m.setTipo("Error");
            m.setMsj("Error Mysql" + ex.getMessage());
            m.setDetalles("Error al ingresar los datos:" + ex);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    m.setTipo("Error");
                    m.setMsj("Error Mysql" + ex.getMessage());
                    m.setDetalles("Error al ingresar los datos:" + ex);
                }
            }
        }
        return m;
    }

    @Override
    public empleabilidadTab convertir(ResultSet rs) throws SQLException {
        int id_emplea = rs.getInt("Id");
        String cedula = rs.getString("IdAsp");
        String aspirante = rs.getString("NombreAsp");
        int id_oferta = rs.getInt("IdOferta");
        String oferta = rs.getString("NombreOfer");
        String fecha = rs.getString("Fecha");

        empleabilidadTab oTab = new empleabilidadTab(id_emplea, cedula, aspirante, id_oferta, oferta, fecha);
        return oTab;
    }

    @Override
    public empleabilidadTab obtener(Integer id) {
        PreparedStatement stat = null;
        ResultSet rs = null;

        empleabilidadTab eMod = null;
        try {
            stat = con.prepareCall(Consultar);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                eMod = convertir(rs);
            } else {
                throw new SQLException("Error, empleabilidad no encontrado");
            }
        } catch (SQLException ex) {
            System.out.println("Error de SQL " + ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Error de SQL rs: " + ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    System.out.println("Error de SQL: " + ex);
                }

            }
        }
        return eMod;
    }

    @Override
    public List<empleabilidadTab> listar() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<empleabilidadTab> eLis = new ArrayList<>();
        try {
            try {
                stat = con.prepareCall(ListarTodos);

                rs = stat.executeQuery();
                while (rs.next()) {
                    eLis.add(convertir(rs));
                }
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        System.out.println("Error sql rs: " + ex);
                    }
                }
                if (stat != null) {
                    try {
                        stat.close();
                    } catch (SQLException ex) {
                        System.out.println("Error sql st: " + ex);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error sql: " + ex);
        }
        return eLis;
    }
}
