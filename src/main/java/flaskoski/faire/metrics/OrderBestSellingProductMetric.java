package flaskoski.faire.metrics;

import flaskoski.faire.model.Option;
import flaskoski.faire.model.Order;
import flaskoski.faire.model.OrderItem;

import java.util.*;

public class OrderBestSellingProductMetric implements OrderOptionMetric {
    @Override
    public Map.Entry<Option, Integer> process(Map<String, Order> orderMap) {
        //For checking the best selling product
        List<Option> optionsConsidered = new ArrayList<>();


        for(Order order : orderMap.values()){
            Integer counter;
            //For each order item
            for(OrderItem item : order.getItems()) {
                Option optionToAdd = item.getOptionItemInfo();

                counter = 0;
                for (Option optionAdded : optionsConsidered) {
                    if (optionAdded.getId().equals(optionToAdd.getId())) {
                        optionAdded.setAvailable_quantity(optionAdded.getAvailable_quantity() + optionToAdd.getAvailable_quantity());
                        break;
                    }
                    counter++;
                }
                if(optionsConsidered.size() == counter)
                    optionsConsidered.add(optionToAdd);
            }
        }
        optionsConsidered.sort(Comparator.comparing(Option::getAvailable_quantity).reversed());
        return new HashMap.SimpleEntry<>(optionsConsidered.get(0), optionsConsidered.get(0).getAvailable_quantity() );
    }
}
