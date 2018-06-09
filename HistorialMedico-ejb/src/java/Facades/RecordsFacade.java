/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Records;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author myf19
 */
@Stateless
public class RecordsFacade extends AbstractFacade<Records> {

    @PersistenceContext(unitName = "HistorialMedico-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecordsFacade() {
        super(Records.class);
    }
    
    public void addRecord(Records record){
        String query= "INSERT INTO RECORDS (ID,DNI,INFO,DATE) VALUES (?,?,?,?)";
        em.createNativeQuery(query).setParameter(1, record.getId()).
                            setParameter(2, record.getDni()).
                            setParameter(3, record.getInfo()).
                            setParameter(4, record.getDate()).executeUpdate();
    }

    public List<Records> showRecords(){
        String query="SELECT r FROM Records r";
        return em.createQuery(query).getResultList();
    }
      
    public void updateRecord(Records record){
        String query="UPDATE Records set dni=:dni,info=:info,date=:date where id=:id";
        em.createQuery(query).setParameter("dni", record.getDni()).
                setParameter("info", record.getInfo()).
                setParameter("date", record.getDate()).
                setParameter("id",record.getId()).executeUpdate();
    }
 
    public void deleteRecord(Records record){
        String query= "DELETE FROM Records where id=:id";
        em.createQuery(query).setParameter("id", record.getId()).executeUpdate();
    }
    
    public List<Records> findLastRecord(){
        String query= "SELECT r FROM Records r ORDER BY r.id DESC";
        return em.createQuery(query).getResultList();
    }
    
}
