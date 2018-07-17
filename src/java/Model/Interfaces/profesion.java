/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Interfaces;

import Model.DAO;
import Model.Tablas.profesionTab;
import java.sql.ResultSet;

/**
 *
 * @author freyd
 */
public interface profesion extends DAO <ResultSet, profesionTab, Integer>{
        
}
