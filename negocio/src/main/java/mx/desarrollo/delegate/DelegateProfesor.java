/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.delegate;

import java.util.List;
import java.util.function.Consumer;
import mx.desarrollo.DAO.ProfesoresDAO;
import mx.desarrollo.entidad.Profesores;
import mx.desarrollo.entidad.UnidadesAcademicas;
import mx.desarrollo.integracion.ServiceLocator;
import mx.desarrollo.persistencia.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author joapa
 */
public class DelegateProfesor {
   /**
     * Metodo de ejemplo para guardar Usuario
     * @param profesor de tipo usuario con id 0 para poder que se cree un id nuevo
     */
    
    public void registrarProfesor(Profesores profesor){
        ServiceLocator.getInstanceProfesoresDAO().save(profesor);
    }
    
    public List<Profesores> listaAsignaciones(int idProfesor){
        List<Profesores> Uaprendizaje = null;
        List<Profesores> general = cargarProfesores();
        try {
            for (Profesores profesores : general) {
                Profesores pr = new Profesores();
                if(profesores.getIdProfesor() == idProfesor)
                    pr = profesores;
                Uaprendizaje.add(pr);
            }
            return Uaprendizaje;
        } catch (Exception e) {
        }
        return Uaprendizaje;
    }
    
    public List<Profesores> cargarProfesores() {
        List<Profesores> profesores = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM Profesores";
            Query query = session.createQuery(hql);
            profesores = query.list(); // Asignación correcta del resultado de la consulta
            for (Profesores prof : profesores) { // Usa profesores directamente aquí
                cargarUnidadesDeAprendizajeParaProfesor(prof, session);
            }
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace(); // Manejar la excepción
        } finally {
            session.close();
        }
        return profesores;
    }
    
    //ENCONRAR UNIDADES DE PROFESORES
    private void cargarUnidadesDeAprendizajeParaProfesor(Profesores profesor, Session session) {
        try {
            Query query = session.createQuery("FROM UnidadesAcademicas WHERE idProfesor = :idProfesor");
            query.setParameter("idProfesor", profesor.getIdProfesor());
            List<UnidadesAcademicas> unidades = query.list();
            profesor.setUnidadesAcademicasList(unidades);
        } catch (Exception e) {
        }
    }
    
    public void eliminarProfesor(int profesorId) {
    // Primero, se busca al profesor por su ID para asegurarse de que es una entidad gestionada
    ProfesoresDAO profesoresDAO = ServiceLocator.getInstanceProfesoresDAO();
    Profesores profesor = profesoresDAO.findById(profesorId);
    
    if (profesor != null) {
        // Si el profesor existe, se procede a eliminarlo
        profesoresDAO.delete(profesor);
    } else {
        System.out.println("Profesor no encontrado con el ID: " + profesorId);
    }
    }
}
