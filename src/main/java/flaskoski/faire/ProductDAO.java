package flaskoski.faire;

import flaskoski.faire.model.Option;
import flaskoski.faire.model.Order;
import flaskoski.faire.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProductDAO extends ItemDAO{

    @Override
    public <Product> void add(Product item){
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory(dbSchema);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}