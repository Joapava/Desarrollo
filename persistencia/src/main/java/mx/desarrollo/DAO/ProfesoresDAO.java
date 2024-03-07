/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.DAO;

import mx.desarrollo.entidad.Profesores;
import mx.desarrollo.persistencia.AbstractDAO;
import mx.desarrollo.persistencia.HibernateUtil;
import org.hibernate.Session;


public class ProfesoresDAO extends AbstractDAO<Profesores> {
    public ProfesoresDAO() {
        super(Profesores.class);
    }
    
    public Profesores findById(Integer id) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Profesores profesor = session.get(Profesores.class, id);
    session.close();
    return profesor;
}
}
