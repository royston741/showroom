package com.showroom.Service;

import com.showroom.Entity.Customer;
import com.showroom.Entity.Order;
import com.showroom.Entity.Vehicle;

import java.util.List;

public interface OrderService {
    public Order createOrder(Order order);

    public Order updateOrder(Order order);

    public Order updateOrderByAddingVehicle(Order order, Vehicle newVehicle);

    public Order updateOrderByDeletingVehicle(Order order, Vehicle newVehicle);

    public void deleteOrder(Order order);

    public void deleteOrderById(int id);

    public Order getOrderById(int id);

    public List<Order> getAllOrdersByCustomerNameAndPhoneNo(Customer customer);

}
