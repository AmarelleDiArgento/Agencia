/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Servicios.Mensajes;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author freyd
 */
public interface DAO <R, O, I> {

    Mensajes insertar(O o);

    Mensajes modificar(O o);

    Mensajes eliminar(I id);

    O convertir(R rs) throws SQLException;

    O obtener(I id);
    
    List<O> listar();
    
}
