/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Implementar;

import Model.Interfaces.aspirante;
import Model.Tablas.aspiranteTab;
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
public class aspiranteImp implements aspirante {

    private final Connection con;
    Mensajes m = new Mensajes();

    public aspiranteImp(Connection con) {

        this.con = con;
    }

    final String Insertar = "call Agencia.InsAspNew(?,?,?,?,?,?)";
    final String Modificar = "";
    final String Eliminar = "";
    final String Consultar = "";
    final String ListarTodos = "call Agencia.VerAsp();";

    @Override
    public Mensajes insertar(aspiranteTab a) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = con.prepareStatement(Insertar);
            stat.setString(1, a.getCedula());
            stat.setString(2, a.getNombre());
            stat.setInt(3, a.getEdad());
            stat.setString(4, a.getGenero());
            stat.setInt(5, a.getId_profesion());
            stat.setInt(6, a.getId_agencia());
            if (stat.executeUpdate() == 0) {
                m.setTipo("Error");
                m.setMsj("Error Mysql");
                m.setDetalles("Error al ingresar los datos");
            } else {
                m.setTipo("Ok");
                m.setMsj("Se a agregado exitosamente el ID: " + a.getCedula());
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
    public Mensajes modificar(aspiranteTab a) {
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Modificar);
            stat.setString(1, a.getCedula());
            stat.setString(2, a.getNombre());
            stat.setInt(3, a.getEdad());
            stat.setString(4, a.getGenero());
            stat.setInt(5, a.getId_profesion());
            stat.setInt(6, a.getId_agencia());
            if (stat.executeUpdate() == 0) {

                m.setTipo("Error");
                m.setMsj("Error Mysql");
                m.setDetalles("Error al modificar los datos");
            } else {
                m.setTipo("Ok");
                m.setMsj(a.getNombre() + " modificado exitosamente");
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
    public Mensajes eliminar(String id) {

        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Eliminar);
            stat.setString(1, id);
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
    public aspiranteTab convertir(ResultSet rs) throws SQLException {

        String cedula = rs.getString("NumCedula");
        String nombre = rs.getString("NombreAsp");
        int edad = rs.getInt("Edad");
        String genero = rs.getString("Genero");
        int id_profesion = rs.getInt("CodigoProfesion");
        String profesion = rs.getString("NombreAg");
        int id_agencia = rs.getInt("Edad");
        String agencia = rs.getString("CodigoAgencia");

        aspiranteTab aTab = new aspiranteTab(cedula, nombre, edad, genero, id_profesion, profesion, id_agencia, agencia);
        return aTab;
    }

    @Override
    public aspiranteTab obtener(String id) {
        PreparedStatement stat = null;
        ResultSet rs = null;

        aspiranteTab aMod = null;
        try {
            stat = con.prepareCall(Consultar);
            stat.setString(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                aMod = convertir(rs);
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
        return aMod;
    }

    @Override
    public List<aspiranteTab> listar() {

        PreparedStatement stat = null;
        ResultSet rs = null;
        List<aspiranteTab> aLis = new ArrayList<>();
        try {
            try {
                stat = con.prepareCall(ListarTodos);

                rs = stat.executeQuery();
                while (rs.next()) {
                    aLis.add(convertir(rs));
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
        return aLis;
    }

}
