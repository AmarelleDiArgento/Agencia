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
public class ofertaTab implements Serializable{
    private int id_oferta;
    private String nombre;
    private String descripcion;
    private String fecha_inicio;
    private String fecha_fin;

    public ofertaTab(String nombre, String descripcion, String fecha_inicio, String fecha_fin) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }
    
    public ofertaTab(int id_oferta, String nombre, String descripcion, String fecha_inicio, String fecha_fin) {
        this.id_oferta = id_oferta;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    /**
     * @return the id_oferta
     */
    public int getId_oferta() {
        return id_oferta;
    }

    /**
     * @param id_oferta the id_oferta to set
     */
    public void setId_oferta(int id_oferta) {
        this.id_oferta = id_oferta;
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the fecha_inicio
     */
    public String getFecha_inicio() {
        return fecha_inicio;
    }

    /**
     * @param fecha_inicio the fecha_inicio to set
     */
    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    /**
     * @return the fecha_fin
     */
    public String getFecha_fin() {
        return fecha_fin;
    }

    /**
     * @param fecha_fin the fecha_fin to set
     */
    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    @Override
    public String toString() {
        return "ofertaTab{" + "id_oferta=" + id_oferta + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + '}';
    }

    
    
    
    
}
