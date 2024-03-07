
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.facade;

import java.util.List;
import mx.desarrollo.delegate.DelegateProfesor;
import mx.desarrollo.entidad.Profesores;
/**
 *
 * @author joapa
 */
public class FacadeProfesores {
    
    private final DelegateProfesor delegateProfesor;
    
    public FacadeProfesores() {
        this.delegateProfesor = new DelegateProfesor();
    }
    
   public void saveProfe(Profesores profesor) {
        delegateProfesor.registrarProfesor(profesor);
    }
   
   public List<Profesores> getProfes(){
       return delegateProfesor.cargarProfesores();
   }
   
   public List<Profesores> getAsignaciones(int idProfesor){
       return delegateProfesor.listaAsignaciones(idProfesor);
   }
   
    public void deleteProfe(int id) {
    // Suponiendo que Profesores tiene un método getId() que retorna el ID del profesor.
 
    delegateProfesor.eliminarProfesor(id);
}
}
