/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.DAO;
import mx.desarrollo.entidad.UnidadesAcademicas;
import mx.desarrollo.persistencia.AbstractDAO;

/**
 *
 * @author joapa
 */
public class UnidadAcademicaDAO extends AbstractDAO<UnidadesAcademicas>{
    public UnidadAcademicaDAO(){
        super(UnidadesAcademicas.class);
    }
}
