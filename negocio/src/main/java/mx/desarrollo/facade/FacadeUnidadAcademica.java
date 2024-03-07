/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.facade;
import mx.desarrollo.delegate.DelegateUnidadAcademica;
import mx.desarrollo.entidad.UnidadesAcademicas;

/**
 *
 * @author joapa
 */
public class FacadeUnidadAcademica {
    private final DelegateUnidadAcademica delegateUnidad;
    
    public FacadeUnidadAcademica(){
        this.delegateUnidad = new DelegateUnidadAcademica();
    }
    
    public void guardarUnidad(UnidadesAcademicas unidad){
        delegateUnidad.saveUnidad(unidad);
    }
}
