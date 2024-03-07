/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author joapa
 */
@Entity
@Table(name = "unidades_academicas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadesAcademicas.findAll", query = "SELECT u FROM UnidadesAcademicas u")
    , @NamedQuery(name = "UnidadesAcademicas.findByIdUnidadAprendizaje", query = "SELECT u FROM UnidadesAcademicas u WHERE u.idUnidadAprendizaje = :idUnidadAprendizaje")
    , @NamedQuery(name = "UnidadesAcademicas.findByNombreUnidadAprendizaje", query = "SELECT u FROM UnidadesAcademicas u WHERE u.nombreUnidadAprendizaje = :nombreUnidadAprendizaje")
    , @NamedQuery(name = "UnidadesAcademicas.findByHorasClase", query = "SELECT u FROM UnidadesAcademicas u WHERE u.horasClase = :horasClase")
    , @NamedQuery(name = "UnidadesAcademicas.findByHorasTaller", query = "SELECT u FROM UnidadesAcademicas u WHERE u.horasTaller = :horasTaller")
    , @NamedQuery(name = "UnidadesAcademicas.findByHorasLaboratorio", query = "SELECT u FROM UnidadesAcademicas u WHERE u.horasLaboratorio = :horasLaboratorio")})
public class UnidadesAcademicas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUnidadAprendizaje")
    private Integer idUnidadAprendizaje;
    @Column(name = "nombreUnidadAprendizaje")
    private String nombreUnidadAprendizaje;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "horasClase")
    private BigDecimal horasClase;
    @Column(name = "horasTaller")
    private BigDecimal horasTaller;
    @Column(name = "horasLaboratorio")
    private BigDecimal horasLaboratorio;
    @ManyToMany(mappedBy = "unidadesAcademicasList")
    private List<Profesores> profesoresList;

    public UnidadesAcademicas() {
    }

    public UnidadesAcademicas(Integer idUnidadAprendizaje) {
        this.idUnidadAprendizaje = idUnidadAprendizaje;
    }

    public Integer getIdUnidadAprendizaje() {
        return idUnidadAprendizaje;
    }

    public void setIdUnidadAprendizaje(Integer idUnidadAprendizaje) {
        this.idUnidadAprendizaje = idUnidadAprendizaje;
    }

    public String getNombreUnidadAprendizaje() {
        return nombreUnidadAprendizaje;
    }

    public void setNombreUnidadAprendizaje(String nombreUnidadAprendizaje) {
        this.nombreUnidadAprendizaje = nombreUnidadAprendizaje;
    }

    public BigDecimal getHorasClase() {
        return horasClase;
    }

    public void setHorasClase(BigDecimal horasClase) {
        this.horasClase = horasClase;
    }

    public BigDecimal getHorasTaller() {
        return horasTaller;
    }

    public void setHorasTaller(BigDecimal horasTaller) {
        this.horasTaller = horasTaller;
    }

    public BigDecimal getHorasLaboratorio() {
        return horasLaboratorio;
    }

    public void setHorasLaboratorio(BigDecimal horasLaboratorio) {
        this.horasLaboratorio = horasLaboratorio;
    }

    @XmlTransient
    public List<Profesores> getProfesoresList() {
        return profesoresList;
    }

    public void setProfesoresList(List<Profesores> profesoresList) {
        this.profesoresList = profesoresList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidadAprendizaje != null ? idUnidadAprendizaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadesAcademicas)) {
            return false;
        }
        UnidadesAcademicas other = (UnidadesAcademicas) object;
        if ((this.idUnidadAprendizaje == null && other.idUnidadAprendizaje != null) || (this.idUnidadAprendizaje != null && !this.idUnidadAprendizaje.equals(other.idUnidadAprendizaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.desarrollo.entidad.UnidadesAcademicas[ idUnidadAprendizaje=" + idUnidadAprendizaje + " ]";
    }
    
}
