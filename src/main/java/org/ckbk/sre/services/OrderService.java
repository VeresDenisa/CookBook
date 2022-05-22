package org.ckbk.sre.services;

import org.ckbk.sre.model.Order;
import org.ckbk.sre.model.Product;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.ArrayList;
import java.util.Objects;

public class OrderService {

    private static ObjectRepository<Order> orderRepository;

    public static Order getOrder(int i){
        try{
            return orderRepository.find().toList().get(i);
        }catch(IndexOutOfBoundsException e){
            return null;
        }
    }

    public static ArrayList<Integer> getMySentOrders(){
        ArrayList<Integer> mine = new ArrayList<>();
        var l = orderRepository.find().toList();
        for(Order r : l){
            if(Objects.equals(UserService.getUser().getUsername(), r.getShop()) && r.isSent()) {
                mine.add(l.indexOf(r));
            }
        }
        return mine;
    }

    public static void initDatabase(Nitrite database) {
        orderRepository = database.getRepository(Order.class);
    }

    public static Order findOrder(String username){
        for(Order order : orderRepository.find())
            if(Objects.equals(order.getUsername(), username) && !order.isSent())
                return order;
        return null;
    }

    public static void addOrder(String username, String shop, boolean sent, ArrayList<Product> ingredient, ArrayList<Integer> ingredientQuantity) {
        orderRepository.insert(new Order(username, shop, sent, ingredient, ingredientQuantity));
    }

    public static ObjectRepository<Order> getOrderRepository() {
        return orderRepository;
    }

    public static long getOrderRepositorySize(){
        return orderRepository.size();
    }
}
