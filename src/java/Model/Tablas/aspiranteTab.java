/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Tablas;

import java.io.Serializable;

/**
 *
 * @author freyd
 */
public class aspiranteTab implements Serializable {

    private String cedula;
    private String nombre;
    private int edad;
    private String genero;
    private int id_profesion;
    private String profesion;
    private int id_agencia;
    private String agencia;

    public aspiranteTab(String cedula, String nombre, int edad, String genero, int id_profesion, int id_agencia) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.id_profesion = id_profesion;
        this.id_agencia = id_agencia;
    }

    public aspiranteTab(String cedula, String nombre, int edad, String genero, int id_profesion, String profesion, int id_agencia, String agencia) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.id_profesion = id_profesion;
        this.profesion = profesion;
        this.id_agencia = id_agencia;
        this.agencia = agencia;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the id_profesion
     */
    public int getId_profesion() {
        return id_profesion;
    }

    /**
     * @param id_profesion the id_profesion to set
     */
    public void setId_profesion(int id_profesion) {
        this.id_profesion = id_profesion;
    }

    /**
     * @return the profesion
     */
    public String getProfesion() {
        return profesion;
    }

    /**
     * @param profesion the profesion to set
     */
    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    /**
     * @return the id_agencia
     */
    public int getId_agencia() {
        return id_agencia;
    }

    /**
     * @param id_agencia the id_agencia to set
     */
    public void setId_agencia(int id_agencia) {
        this.id_agencia = id_agencia;
    }

    /**
     * @return the agencia
     */
    public String getAgencia() {
        return agencia;
    }

    /**
     * @param agencia the agencia to set
     */
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }
    
    
    
    
}
