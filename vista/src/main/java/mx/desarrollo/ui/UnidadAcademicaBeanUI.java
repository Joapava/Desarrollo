/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.ui;

import java.io.Serializable;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import mx.desarrollo.entidad.UnidadesAcademicas;
import mx.desarrollo.helper.UnidadHelper;

/**
 *
 * @author joapa
 */

@ManagedBean(name = "unidadUI")
@SessionScoped
public class UnidadAcademicaBeanUI implements Serializable{
    private final UnidadHelper unidadhelper;
    private UnidadesAcademicas unidad;
    
    public UnidadAcademicaBeanUI(){
        unidadhelper = new UnidadHelper();
    }
    
    @PostConstruct
    public void init(){
        unidad= new UnidadesAcademicas();
    }
    
    public void save() throws IOException{
        BigDecimal cero = new BigDecimal("0");
        // Validación del nombre de la unidad de aprendizaje: solo caracteres
        if (!unidad.getNombreUnidadAprendizaje().matches("[a-zA-Z\\s]+")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El nombre de la unidad de aprendizaje solo debe contener letras."));
            return;
        }
         // Validaciones para horasClase, horasTaller y horasLaboratorio sean mayores a 0
        if (unidad.getHorasClase() != null && unidad.getHorasClase().compareTo(cero) <= 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las horas de clase deben ser mayores a 0."));
            return;
        }
        if (unidad.getHorasTaller() != null && unidad.getHorasTaller().compareTo(cero) <= 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las horas de taller deben ser mayores a 0."));
            return;
        }
         if (unidad.getHorasLaboratorio() != null && unidad.getHorasLaboratorio().compareTo(cero) <= 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las horas de laboratorio deben ser mayores a 0."));
            return;
        }

        // Intento de guardar la unidad académica
        try {
            unidadhelper.save(unidad);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Unidad Académica guardada con éxito."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al guardar la unidad académica."));
        } finally {
            // Restablecer el objeto unidad después de guardar o en caso de error
            unidad = new UnidadesAcademicas();
        }
    }
    
    public UnidadesAcademicas getUnidadesAcademicas(){
        return unidad;
    }
    
    public void setUnidadesAcademicas(UnidadesAcademicas unidad){
        this.unidad = unidad;
    }
}
