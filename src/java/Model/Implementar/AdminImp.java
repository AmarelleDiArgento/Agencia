/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Implementar;

import Model.Interfaces.Admin;
import Model.Interfaces.agencia;
import Model.Interfaces.aspirante;
import Model.Interfaces.empleabilidad;
import Model.Interfaces.oferta;
import Model.Interfaces.profesion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author freyd
 */
public class AdminImp implements Admin {

    private Connection con = null;
    private DataSource pool = null;

    public AdminImp() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Agencia", "AgenciaBd", "AgenciaBd");
        } catch (ClassNotFoundException ex) {
            System.out.print("Class for name " + ex);
        }
    }

    public AdminImp(DataSource ds) throws SQLException {
        this.pool = ds;
        this.con = pool.getConnection();
    }
    
    
    private agencia age = null;
    private aspirante asp = null;
    private empleabilidad emp = null;
    private oferta ofe = null;
    private profesion pro = null;
    

    @Override
    public agencia getAgencia() {
        if (age == null) {
            age = new agenciaImp(con);
        }
        return age;
    }

    @Override
    public aspirante getAspirante() {
        if (asp == null) {
            asp = new aspiranteImp(con);
        }
        return asp;
    }

    @Override
    public empleabilidad getEmpleabilidad() {
        if (emp == null) {
            emp = new empleabilidadImp(con);
        }
        return emp;
    }

    @Override
    public oferta getOferta() {
        if (ofe == null) {
            ofe = new ofertaImp(con);
        }
        return ofe;
    }

    @Override
    public profesion getProfecion() {
        if (pro == null) {
            pro = new profesionImp(con);
        }
        return pro;
    }

}
