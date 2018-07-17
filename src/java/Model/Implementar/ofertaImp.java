/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Implementar;

import Model.Interfaces.oferta;
import Model.Tablas.ofertaTab;
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
public class ofertaImp implements oferta {

    private final Connection con;
    Mensajes m = new Mensajes();

    public ofertaImp(Connection con) {

        this.con = con;
    }

    final String Insertar = "call Agencia.InsOfertaNew(?,?,?,?);";
    final String Modificar = "";
    final String Eliminar = "";
    final String Consultar = "";
    final String ListarTodos = "call Agencia.VerOfertas();";

    @Override
    public Mensajes insertar(ofertaTab o) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = con.prepareStatement(Insertar);
            stat.setString(1, o.getNombre());
            stat.setString(2, o.getDescripcion());
            stat.setString(3, o.getFecha_inicio());
            stat.setString(4, o.getFecha_fin());
            if (stat.executeUpdate() == 0) {
                m.setTipo("Error");
                m.setMsj("Error Mysql");
                m.setDetalles("Error al ingresar los datos");
            } else {
                m.setTipo("Ok");
                m.setMsj("Se a insertado exitosamente la oferta: " + o.getNombre());
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
    public Mensajes modificar(ofertaTab o) {

        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Modificar);
            stat.setInt(1, o.getId_oferta());
            stat.setString(2, o.getNombre());
            stat.setString(3, o.getDescripcion());
            stat.setString(4, o.getFecha_inicio());
            stat.setString(5, o.getFecha_fin());
            if (stat.executeUpdate() == 0) {

                m.setTipo("Error");
                m.setMsj("Error Mysql");
                m.setDetalles("Error al modificar los datos");
            } else {
                m.setTipo("Ok");
                m.setMsj(o.getNombre() + " modificado exitosamente");
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
    public ofertaTab convertir(ResultSet rs) throws SQLException {

        int id_oferta = rs.getInt("IdOfer");
        String nombre = rs.getString("NombreOfer");
        String descripcion = rs.getString("Descripcion");
        String feInicio = rs.getString("fechaInicio");
        String feFin = rs.getString("FechaFin");

        ofertaTab oTab = new ofertaTab(id_oferta, nombre, descripcion, feInicio, feFin);
        return oTab;
    }

    @Override
    public ofertaTab obtener(Integer id) {
        PreparedStatement stat = null;
        ResultSet rs = null;

        ofertaTab oMod = null;
        try {
            stat = con.prepareCall(Consultar);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                oMod = convertir(rs);
            } else {
                throw new SQLException("Error, agencia no encontrado");
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
        return oMod;
    }

    @Override
    public List<ofertaTab> listar() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<ofertaTab> oLis = new ArrayList<>();
        try {
            try {
                stat = con.prepareCall(ListarTodos);

                rs = stat.executeQuery();
                while (rs.next()) {
                    oLis.add(convertir(rs));
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
        return oLis;
    }
}
