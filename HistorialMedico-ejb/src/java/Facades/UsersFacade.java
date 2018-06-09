/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author myf19
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "HistorialMedico-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    public List<Users> showUsers(){
        String query="SELECT u FROM Users u";
        return em.createQuery(query).getResultList();
    }
    
    public List<Users> findLastUser(){
        String query= "SELECT u FROM Users u ORDER BY u.id DESC";
        return em.createQuery(query).getResultList();
    }
    
    public List<Users> findRecordbyDni(String dni){
        String query= "SELECT u FROM Users u WHERE u.dni = :dni";
        return em.createQuery(query).setParameter("dni", dni).getResultList();
    }
}