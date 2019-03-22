package flaskoski.faire.DAO;

import flaskoski.faire.model.Product;
import flaskoski.faire.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ItemDAO {



    public <T> void add(T item){
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();

        em.close();
    }

    public <T> void addAll(List<T> items) {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();
        for(T item : items)
            em.persist(item);
        em.getTransaction().commit();

        em.close();
    }
    //public <T> void get(T item);
}
