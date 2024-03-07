/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.delegate;
import mx.desarrollo.entidad.UnidadesAcademicas;
import mx.desarrollo.integracion.ServiceLocator;
/**
 *
 * @author joapa
 */
public class DelegateUnidadAcademica {
    /**
     * Metodo de ejemplo para guardar Alumno
     * @param unidad de tipo usuario con id 0 para que se cree un id nuevo
     */
    public void saveUnidad(UnidadesAcademicas unidad){
        ServiceLocator.getInstanceUsuarioDAO().save(unidad);
    }
}
