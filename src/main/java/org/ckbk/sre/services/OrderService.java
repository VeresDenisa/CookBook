package org.ckbk.sre.services;

import org.ckbk.sre.model.Order;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.ArrayList;

public class OrderService {

    private static ObjectRepository<Order> orderRepository;

    public static void initDatabase(Nitrite database) {
        orderRepository = database.getRepository(Order.class);
    }

    public static ArrayList<Integer> getOrders(){
        return null;
    }

    public static void addOrder(String username) {
        orderRepository.insert(new Order(username));
    }

    public static long getOrderRepositorySize(){
        return orderRepository.size();
    }
}
