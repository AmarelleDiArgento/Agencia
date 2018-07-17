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
public class profesionTab implements Serializable {

    private int id_profesion;
    private String Nombre;

    public profesionTab(String Nombre) {
        this.Nombre = Nombre;
    }

    public profesionTab(int id_profesion, String Nombre) {
        this.id_profesion = id_profesion;
        this.Nombre = Nombre;
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
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public String toString() {
        return "profesionTab{" + "id_profesion=" + id_profesion + ", Nombre=" + Nombre + '}';
    }

}
