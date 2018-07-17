/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Model.Implementar.AdminImp;
import Model.Interfaces.agencia;
import Model.Tablas.agenciaTab;
import Model.Tablas.aspiranteTab;
import Model.Tablas.empleabilidadTab;
import Model.Tablas.ofertaTab;
import Model.Tablas.profesionTab;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Amarelle
 */
public class Probador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mensajes m = new Mensajes();
        try {
            AdminImp Asql = new AdminImp();
            agenciaTab a = new agenciaTab(2323, "Cosa", "12", "1212");
            m = Asql.getAgencia().insertar(a);
            /*
            // insertar agencia
            agenciaTab a = new agenciaTab(2323, "Cosa", "12", "1212");
            m = Asql.getAgencia().insertar(a);
            
            // Listar Agencias
            List<agenciaTab> aList = Asql.getAgencia().listar();
            for (agenciaTab a : aList) {
                System.out.println(a.toString());
            }
            
             */
            
            /*
            // insertar aspirante
            aspiranteTab a = new aspiranteTab("12345","Pepe",12,"Masculino",1,1212);
            m = Asql.getAspirante().insertar(a);
 
            // Listar Agencias
            List<aspiranteTab> aList = Asql.getAspirante().listar();
            for (aspiranteTab a : aList) {
                System.out.println(a.toString());
            }
             */
 /*
            // insertar empleabilidad
            empleabilidadTab a = new empleabilidadTab("12345",1,"20180610");
            m = Asql.getEmpleabilidad().insertar(a);
            // Listar empleabilidades
            
            List<empleabilidadTab> eList = Asql.getEmpleabilidad().listar();
            
            for (empleabilidadTab e : eList) {
                System.out.println(e.toString());
            }
             */
 /*
            // insertar oferta
            ofertaTab a = new ofertaTab("Blaaa Blaaa Blaaa Blaaa ","Blaaa Blaaa Blaaa Blaaa Blaaa Blaaa Blaaa","20180701","20180706");
            m = Asql.getOferta().insertar(a);
            
            // Listar ofertas
            List<ofertaTab> eList = Asql.getOferta().listar();
            
            for (ofertaTab e : eList) {
                System.out.println(e.toString());
            }
             */
 /*
            // insertar profesion
            profesionTab a = new profesionTab("Zapatero");
            m = Asql.getProfecion().insertar(a);
            
            // Listar profesiones
            List<profesionTab> pList = Asql.getProfesion().listar();
            
            for (profesionTab p : pList) {
                System.out.println(p.toString());
            }
             */
            // Listar profesiones
            List<profesionTab> pList = Asql.getProfesion().listar();

            for (profesionTab p : pList) {
                System.out.println(p.toString());
            }
        } catch (SQLException ex) {
            m.setTipo("Error");
            m.setMsj("Error " + ex.getLocalizedMessage());
            m.setDetalles("Error sql: " + ex);
        }

        System.out.println(m.getTipo());
        System.out.println(m.getMsj());
        System.out.println(m.getDetalles());

    }
}
