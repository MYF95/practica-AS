/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Patients;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author myf19
 */
@Stateless
public class PatientsFacade extends AbstractFacade<Patients> {

    @PersistenceContext(unitName = "HistorialMedico-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PatientsFacade() {
        super(Patients.class);
    }
    
    public void addPatient(Patients patient){
        String query= "INSERT INTO PATIENTS (ID,DNI,NAME,AGE) VALUES (?,?,?,?)";
        em.createNativeQuery(query).setParameter(1, patient.getId()).
                            setParameter(2, patient.getDni()).
                            setParameter(3, patient.getName()).
                            setParameter(4, patient.getAge()).executeUpdate();
    }

    public List<Patients> showPatients(){
        String query="SELECT p FROM Patients p";
        return em.createQuery(query).getResultList();
    }
      
    public void updatePatient(Patients patient){
        String query="UPDATE Patients set dni=:dni,name=:name,age=:age where id=:id";
        em.createQuery(query).setParameter("age", patient.getAge()).
                setParameter("dni", patient.getDni()).
                setParameter("name", patient.getName()).
                setParameter("id",patient.getId()).executeUpdate();
    }
 
    public void deletePatient(Patients patient){
        String query= "DELETE FROM Patients where id=:id";
        em.createQuery(query).setParameter("id", patient.getId()).executeUpdate();
    }
    
    public List<Patients> findLastPatient(){
        String query= "SELECT p FROM Patients p ORDER BY p.id DESC";
        return em.createQuery(query).getResultList();
    }
    
   
}
