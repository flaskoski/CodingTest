package flaskoski.faire;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ItemDAO {
    public static final String dbSchema = "fairedb";


    public <T> void add(T item){
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory(dbSchema);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
    //public <T> void get(T item);
}
