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
    private int id_aspirante;
    private int id_oferta;
    private String fecha;

    
    public empleabilidadTab(int id_aspirante, int id_oferta, String fecha) {
        this.id_aspirante = id_aspirante;
        this.id_oferta = id_oferta;
        this.fecha = fecha;
    }

    public empleabilidadTab(int id_empleabilidad, int id_aspirante, int id_oferta, String fecha) {
        this.id_empleabilidad = id_empleabilidad;
        this.id_aspirante = id_aspirante;
        this.id_oferta = id_oferta;
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
    public int getId_aspirante() {
        return id_aspirante;
    }

    /**
     * @param id_aspirante the id_aspirante to set
     */
    public void setId_aspirante(int id_aspirante) {
        this.id_aspirante = id_aspirante;
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
    
    
    
    
}
