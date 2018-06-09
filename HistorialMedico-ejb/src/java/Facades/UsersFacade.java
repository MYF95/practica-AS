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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
    
    public List<Users> sortUsersByDni(){
       CriteriaBuilder crit = em.getCriteriaBuilder();
       CriteriaQuery<Users> query = crit.createQuery(Users.class);
       Root<Users> root = query.from(Users.class);
       query.select(root).orderBy(crit.asc(root.get("dni")));
       return em.createQuery(query).getResultList();
    }
    
    public List<Users> showUsers(){
        String query="SELECT u FROM Users u";
        return em.createQuery(query).getResultList();
    }
    
    public List<Users> findLastUser(){
        String query= "SELECT u FROM Users u ORDER BY u.id DESC";
        return em.createQuery(query).getResultList();
    }
    
    public List<Users> findUserbyDni(String dni){
        String query= "SELECT u FROM Users u WHERE u.dni = :dni";
        return em.createQuery(query).setParameter("dni", dni).getResultList();
    }
    
    public List<Users> findUserbyId(int id){
        String query= "SELECT u FROM Users u WHERE u.id = :id";
        return em.createQuery(query).setParameter("id", id).getResultList();
    }
}
