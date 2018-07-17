/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Interfaces;

import Model.DAO;
import Model.Tablas.aspiranteTab;
import java.sql.ResultSet;

/**
 *
 * @author freyd
 */
public interface aspirante extends DAO <ResultSet, aspiranteTab, String>{
    
}
