package flaskoski.faire;


import flaskoski.faire.model.Option;
import flaskoski.faire.model.OrderItem;
import flaskoski.faire.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

public class OrderItemDAO extends ItemDAO{


    public Boolean checkIfItemsAvailable(List<OrderItem> items) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        Query query;
        String queryString;
        //queryString = "select opt.available_quantity from Option opt where opt.id in :Ids";
        queryString = "select opt from option_table opt where opt.sku in :skuIds and opt.active = :true";
        List<String> Ids = Arrays.asList((items.stream().map(OrderItem::getSku).toArray(String[]::new)));
        query = em.createQuery(queryString);
        query.setParameter("skuIds", Ids);
        query.setParameter("true", true);
        List<Option> availableAmounts = query.getResultList();
        int counter = 0;
        List<Integer> neededAmounts =  Arrays.asList(items.stream().map(OrderItem::getQuantity).toArray(Integer[]::new));
//        for(Integer i=0; i<neededAmounts.size(); i++)
//            if(neededAmounts.get(i) > availableAmounts.get(i))
//                break;

        em.getTransaction().commit();
        em.close();

        return true;
    }
}
