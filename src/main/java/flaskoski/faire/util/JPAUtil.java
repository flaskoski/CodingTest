package flaskoski.faire.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    //public static final String dbSchema = "fairedb";
    private static EntityManagerFactory emf =  Persistence.createEntityManagerFactory("fairedb");

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
