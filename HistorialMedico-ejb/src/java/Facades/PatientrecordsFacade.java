/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Patientrecords;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author myf19
 */
@Stateless
public class PatientrecordsFacade extends AbstractFacade<Patientrecords> {

    @PersistenceContext(unitName = "HistorialMedico-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PatientrecordsFacade() {
        super(Patientrecords.class);
    }
    
    public List<Patientrecords> showPatientrecords(){
        String query="SELECT p FROM Patientrecords p";
        return em.createQuery(query).getResultList();
    }
}
