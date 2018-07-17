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
public class empleabilidadTab implements Serializable {

    private int id_empleabilidad;
    private String id_aspirante;
    private String aspirante;
    private int id_oferta;
    private String oferta;
    private String fecha;

    public empleabilidadTab(String id_aspirante, int id_oferta, String fecha) {
        this.id_aspirante = id_aspirante;
        this.id_oferta = id_oferta;
        this.fecha = fecha;
    }

    public empleabilidadTab(int id_empleabilidad, String id_aspirante, String aspirante, int id_oferta, String oferta, String fecha) {
        this.id_empleabilidad = id_empleabilidad;
        this.id_aspirante = id_aspirante;
        this.aspirante = aspirante;
        this.id_oferta = id_oferta;
        this.oferta = oferta;
        this.fecha = fecha;
    }

    /**
     * @return the id_empleabilidad
     */
    public int getId_empleabilidad() {
        return id_empleabilidad;
    }

    /**
     * @param id_empleabilidad the id_empleabilidad to set
     */
    public void setId_empleabilidad(int id_empleabilidad) {
        this.id_empleabilidad = id_empleabilidad;
    }

    /**
     * @return the id_aspirante
     */
    public String getId_aspirante() {
        return id_aspirante;
    }

    /**
     * @param id_aspirante the id_aspirante to set
     */
    public void setId_aspirante(String id_aspirante) {
        this.id_aspirante = id_aspirante;
    }

    /**
     * @return the aspirante
     */
    public String getAspirante() {
        return aspirante;
    }

    /**
     * @param aspirante the aspirante to set
     */
    public void setAspirante(String aspirante) {
        this.aspirante = aspirante;
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
     * @return the oferta
     */
    public String getOferta() {
        return oferta;
    }

    /**
     * @param oferta the oferta to set
     */
    public void setOferta(String oferta) {
        this.oferta = oferta;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "empleabilidadTab{" + "id_empleabilidad=" + id_empleabilidad + ", id_aspirante=" + id_aspirante + ", aspirante=" + aspirante + ", id_oferta=" + id_oferta + ", oferta=" + oferta + ", fecha=" + fecha + '}';
    }
    
    
    

    
}
