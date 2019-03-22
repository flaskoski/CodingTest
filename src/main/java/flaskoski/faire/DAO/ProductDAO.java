package flaskoski.faire.DAO;

import flaskoski.faire.util.JPAUtil;

import javax.persistence.EntityManager;

public class ProductDAO extends ItemDAO{

    @Override
    public <Product> void add(Product item){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();

        em.close();
    }
}
