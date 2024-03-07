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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import mx.desarrollo.entidad.Profesores;
import mx.desarrollo.helper.ProfesorHelper;
import mx.desarrollo.persistencia.HibernateUtil;
import mx.desarrollo.entidad.UnidadesAcademicas;
import org.hibernate.Query;

/**
 *
 * @author joapa
 */
@ManagedBean(name = "profesorUI")
@SessionScoped
public class ProfesorManagedBean implements Serializable {

    private final ProfesorHelper profesorHelper;
    private Profesores profesor;
    private List<Profesores> listaProfesores;
private Profesores profesorSeleccionado;
private int idUnidadAprendizajeSeleccionada; // Para almacenar el ID de la unidad seleccionada
private List<Profesores> profesoresFiltrados;
private List<UnidadesAcademicas> unidadesAprendizajeDisponibles;



    public ProfesorManagedBean() {
        profesorHelper = new ProfesorHelper();

    }

    @PostConstruct
    public void init() {
        profesor = new Profesores();
        listaProfesores = profesorHelper.getProfes();
    }

    // Métodos de acción
    public void registrarProfesor() throws IOException {
        if (!profesor.getNombre().matches("[a-zA-Z\\s]+") || !profesor.getApellido().matches("[a-zA-Z\\s]+")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El nombre y apellido solo deben contener letras."));
            return;
        }
        // Validar el RFC
        if (!profesor.getRfc().matches("[a-zA-Z0-9]{13}")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El RFC debe contener 13 caracteres alfanuméricos."));
            return;
        }

        try {
            // Aquí tu lógica para guardar el profesor
            profesorHelper.alta(profesor);

            // Restablecer el objeto profesor después de guardar
            profesor = new Profesores();

            // Mostrar mensaje de éxito
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Docente registrado exitosamente."));
        } catch (Exception e) {
            // Manejo de errores de registro, por ejemplo, excepción de base de datos
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al registrar el docente."));
        }

    }
    

    public String irVistaLogin() {
        // Aquí puedes añadir cualquier lógica necesaria antes de la navegación
        return "login"; // Retorna el nombre de la vista que quieres mostrar
    }

public void borrarProfe() throws IOException{
    if (profesorSeleccionado != null && profesorSeleccionado.getIdProfesor() != null) {
        profesorHelper.borrar(profesorSeleccionado.getIdProfesor());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Profesor eliminado"));
        // Actualizar la lista de profesores después de la eliminación
        listaProfesores = cargarProfesores();
    } else {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se ha seleccionado ningún profesor para eliminar"));
    }
}
    
public Profesores getProfesorSeleccionado() {
    return profesorSeleccionado;
}

public void setProfesorSeleccionado(Profesores profesorSeleccionado) {
    this.profesorSeleccionado = profesorSeleccionado;
}


public void filtrarProfesoresPorUnidad() {
    Session session = HibernateUtil.getSessionFactory().openSession();
    try {
        String hql = "SELECT p FROM Profesores p JOIN p.unidadesAcademicasList u WHERE u.id = :idUnidadAprendizaje";
        Query query = session.createQuery(hql); // Crea la consulta sin especificar el tipo de resultado
        query.setParameter("idUnidadAprendizaje", idUnidadAprendizajeSeleccionada); // Establece el parámetro por nombre

        profesoresFiltrados = query.list(); // Ejecuta la consulta y recupera el resultado
    } catch (Exception e) {
        e.printStackTrace(); // Maneja adecuadamente la excepción
    } finally {
        if (session != null) {
            session.close(); // Cierra la sesión para liberar recursos
        }
    }
}

public int getIdUnidadAprendizajeSeleccionada() {
    return idUnidadAprendizajeSeleccionada;
}

public void setIdUnidadAprendizajeSeleccionada(int idUnidadAprendizajeSeleccionada) {
    this.idUnidadAprendizajeSeleccionada = idUnidadAprendizajeSeleccionada;
}

public List<Profesores> getProfesoresFiltrados() {
    return profesoresFiltrados;
}

public void setProfesoresFiltrados(List<Profesores> profesoresFiltrados) {
    this.profesoresFiltrados = profesoresFiltrados;
}

public List<UnidadesAcademicas> getUnidadesAprendizajeDisponibles() {
    return unidadesAprendizajeDisponibles;
}

    // Getters y setters
    public Profesores getProfesores() {
        return profesor;
    }

    public void setProfesores(Profesores profesor) {
        this.profesor = profesor;
    }

    public List<Profesores> cargarProfesores() {
        return profesorHelper.getProfes();

    }

    //ENCONTRAR PROFESORES
    // Getters y setters
    public List<Profesores> getListaProfesores() {
        return listaProfesores;
    }

    public void setListaProfesores(List<Profesores> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }
    public void logout() throws IOException{
         String appURL = "/login.xhtml";
         FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + appURL);
     }
}
