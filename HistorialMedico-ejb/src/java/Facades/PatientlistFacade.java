/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Patientlist;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author myf19
 */
@Stateless
public class PatientlistFacade extends AbstractFacade<Patientlist> {

    @PersistenceContext(unitName = "HistorialMedico-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PatientlistFacade() {
        super(Patientlist.class);
    }
    
    public List<Patientlist> showPatientList(int id){
        String query="SELECT p FROM Patientlist p WHERE p.userid = :userid";
        return em.createQuery(query).setParameter("userid", id).getResultList();
    }
    
    public List<Patientlist> showAllPatientList(){
        String query="SELECT p FROM Patientlist p";
        return em.createQuery(query).getResultList();
    }
    
}
