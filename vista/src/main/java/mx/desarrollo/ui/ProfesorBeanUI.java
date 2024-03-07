/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.ui;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import mx.desarrollo.entidad.Profesores;
import mx.desarrollo.helper.ProfesorHelper;

/**
 *
 * @author joapa
 */
@ManagedBean(name = "prUI")
@SessionScoped
public class ProfesorBeanUI implements Serializable{
    private final ProfesorHelper profesorHelper;
    private Profesores profesor;
    private List<Profesores> listaAsignaciones;  
    private int id;
    
    public ProfesorBeanUI(){
        profesorHelper = new ProfesorHelper();
        //listaAsignaciones = profesorHelper.getAsignaciones(3);
    }
    
    @PostConstruct
    public void init(){
        profesor= new Profesores();
        listaAsignaciones = profesorHelper.getAsignaciones(3);
    }
    
    public List<Profesores> lAsignaciones(){
        return profesorHelper.getAsignaciones(profesor.getIdProfesor());
    }
    
    public List<Profesores> buscar(){
        List<Profesores> Asignaciones = null;
        
        return Asignaciones;
    }
    
    public List<Profesores> getlistaAsignaciones(){
        return listaAsignaciones;
    }
    
    public void setlistaAsiganaciones(List<Profesores> listaAsignaciones){
        this.listaAsignaciones = listaAsignaciones;
    }
    
    public void setId(int idProfesor){
        id = idProfesor;
    }
    
}
