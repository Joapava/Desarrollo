/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.helper;

import java.io.Serializable;
import java.util.List;
import mx.desarrollo.integracion.ServiceFacadeLocator;
import mx.desarrollo.entidad.Profesores;


/**
 *
 * @author joapa
 */
public class ProfesorHelper implements Serializable{
    /**
     * Metodo de ejemplo para guardar Profesor
     * @param profesor
     */
    
    public void alta(Profesores profesor){
        ServiceFacadeLocator.getInstanceFacadeProfesores().saveProfe(profesor);
    }
    
    public List<Profesores> getProfes(){
        return ServiceFacadeLocator.getInstanceFacadeProfesores().getProfes();
    }
    
    public List<Profesores> getAsignaciones(int idProfesor){
        return ServiceFacadeLocator.getInstanceFacadeProfesores().getAsignaciones(idProfesor);
    }
    
    public void borrar(int id){
        ServiceFacadeLocator.getInstanceFacadeProfesores().deleteProfe(id);
    }
    
    
}
