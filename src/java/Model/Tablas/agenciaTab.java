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
public class agenciaTab implements Serializable {

    private int id_agencia;
    private String nombre;
    private String telefono;
    private String direccion;

    public agenciaTab(String nombre, String telefono, String direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public agenciaTab(int id_agencia, String nombre, String telefono, String direccion) {
        this.id_agencia = id_agencia;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
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
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "agenciaTab{" + "id_agencia=" + id_agencia + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion + '}';
    }

}
