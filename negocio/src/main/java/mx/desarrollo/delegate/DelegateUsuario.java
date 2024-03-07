/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.delegate;

import java.util.List;
import mx.desarrollo.entidad.Profesores;
import mx.desarrollo.entidad.UnidadesAcademicas;
import mx.desarrollo.entidad.Usuario;
import mx.desarrollo.integracion.ServiceLocator;
import mx.desarrollo.persistencia.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author EduardoCardona <>
 */
public class DelegateUsuario {
    
    /**
     * Metodo para verificar si el usuario esta registrado en la bd
     * @param password 
     * @param correo
     * @return un tipo usuario si no encuntra el usuario sera null
     */
    public Usuario login(String password, String correo){
        Usuario usuario = new Usuario();
        List<Usuario> usuarios = listaUsuarios();
        try {
            for(Usuario us:usuarios){
                if(us.getContrasena().equalsIgnoreCase(password) && us.getCorreo().equalsIgnoreCase(correo)){
                    usuario = us;
                }
            }
        } catch (Exception e) {
            return null;
        }
        
        return usuario;
    }
    
    public List<Usuario> listaUsuarios() {
    List<Usuario> usuarios = null;
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;
    try {
        tx = session.beginTransaction();
        String hql = "FROM Usuario";
        Query query = session.createQuery(hql);
        usuarios = query.list(); // Asignación correcta del resultado de la consulta
        tx.commit();
    } catch (RuntimeException e) {
        if (tx != null) tx.rollback();
        e.printStackTrace(); // Manejar la excepción
    } finally {
        session.close();
    }
    return usuarios;
}
    
    /**
     * Metodo de ejemplo para guardar Usuario
     * @param usuario de tipo usuario con id 0 para poder que se cree un id nuevo
     */
    public void saveUsario(Usuario usuario){
        ServiceLocator.getInstanceUsuarioDAO().save(usuario);
    }
    
}
