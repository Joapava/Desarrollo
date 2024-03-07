/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.helper;

import java.io.Serializable;
import mx.desarrollo.integracion.ServiceFacadeLocator;
import mx.desarrollo.entidad.UnidadesAcademicas;

/**
 *
 * @author joapa
 */
public class UnidadHelper implements Serializable{
    /**
     * Metodo de ejemplo para guardar Profesor
     * @param unidad
     */
    public void save(UnidadesAcademicas unidad){
        ServiceFacadeLocator.getInstanceFacadeUnidad().guardarUnidad(unidad);
    }
}
