/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Implementar;

import Model.Interfaces.profesion;
import Model.Tablas.profesionTab;
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
public class profesionImp implements profesion {

    private final Connection con;
    Mensajes m = new Mensajes();

    public profesionImp(Connection con) {

        this.con = con;
    }

    final String Insertar = "call Agencia.InsProNew(?);";
    final String Modificar = "";
    final String Eliminar = "";
    final String Consultar = "";
    final String ListarTodos = "call Agencia.VerProfesion();";

    @Override
    public Mensajes insertar(profesionTab p) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = con.prepareStatement(Insertar);
            stat.setString(1, p.getNombre());
            if (stat.executeUpdate() == 0) {
                m.setTipo("Error");
                m.setMsj("Error Mysql");
                m.setDetalles("Error al ingresar los datos");
            } else {
                m.setTipo("Ok");
                m.setMsj("Se a insertado exitosamente la profesion: " + p.getNombre());
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
    public Mensajes modificar(profesionTab p) {

        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Modificar);
            stat.setInt(1, p.getId_profesion());
            stat.setString(2, p.getNombre());
            if (stat.executeUpdate() == 0) {

                m.setTipo("Error");
                m.setMsj("Error Mysql");
                m.setDetalles("Error al modificar los datos");
            } else {
                m.setTipo("Ok");
                m.setMsj(p.getNombre() + " modificado exitosamente");
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
    public profesionTab convertir(ResultSet rs) throws SQLException {

        int id_profesion = rs.getInt("IdProfesion");
        String nombre = rs.getString("NomProf");

        profesionTab pTab = new profesionTab(id_profesion, nombre);
        return pTab;
    }

    @Override
    public profesionTab obtener(Integer id) {

        PreparedStatement stat = null;
        ResultSet rs = null;

        profesionTab pMod = null;
        try {
            stat = con.prepareCall(Consultar);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                pMod = convertir(rs);
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
        return pMod;
    }

    @Override
    public List<profesionTab> listar() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<profesionTab> pLis = new ArrayList<>();
        try {
            try {
                stat = con.prepareCall(ListarTodos);

                rs = stat.executeQuery();
                while (rs.next()) {
                    pLis.add(convertir(rs));
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
        return pLis;
    }

}
