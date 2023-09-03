package com.showroom.ServiceImpl;

import com.showroom.Entity.Customer;
import com.showroom.Entity.Order;
import com.showroom.Entity.Vehicle;
import com.showroom.Repository.CustomerDao;
import com.showroom.Repository.OrderDao;
import com.showroom.Repository.VehicleDao;
import com.showroom.Service.CustomerService;
import com.showroom.Service.OrderService;
import com.showroom.Service.VehicleService;
import com.showroom.Util.ValidationUtil;
import com.showroom.constants.Color;
import com.showroom.constants.FuelType;
import com.showroom.constants.TwoWheelerType;
import com.showroom.constants.VehicleType;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private static final Logger log = Logger.getLogger(ValidationUtil.class);
    VehicleService vehicleService = new VehicleServiceImpl();
    OrderDao orderDao = new OrderDao();

    @Override
    public Order createOrder(Order order) {
        List<Vehicle> orderedVehicleList = order.getVehicles().stream().map(vehicle -> vehicleService.createVehicle(vehicle)).collect(Collectors.toList());

        Integer orderTotal = orderedVehicleList.stream().map(vehicle -> {
                    return (vehicle.getPrice()).intValue();
                }).
                reduce(0, Integer::sum);

        order.setVehicles(orderedVehicleList);

        order.setOrderTotal(orderTotal);

        log.info("Name    |     " +
                "Quantity    |     " +
                "Price    |     " +
                "Model no.    |   " +
                "Color     |   " +
                "Fuel type     |   " +
                "Two wheeler    |    "
        );
        order.getVehicles().stream().forEach(vehicle -> {
            vehicleService.printVehicles(vehicle);
        });
        log.info("Order total :" + order.getOrderTotal());
        return orderDao.insertOrder(order);
    }
    @Override
    public Order updateOrder(Order order) {
        return orderDao.updateOrder(order);
    }
    @Override
    public Order updateOrderByAddingVehicle(Order order, Vehicle newVehicle) {
        order.addItem(vehicleService.createVehicle(newVehicle));
        Integer orderTotal = order.getVehicles().stream().map(vehicle -> {
                    return (vehicle.getPrice()).intValue();
                }).
                reduce(0, Integer::sum);
        order.setOrderTotal(orderTotal);
        Order updatedOrder = orderDao.updateOrder(order);
        log.info("------ UPDATED ORDERS ------");
        log.info("Name    |     " +
                "Quantity    |     " +
                "Price    |     " +
                "Model no.    |   " +
                "Color     |   " +
                "Fuel type     |   " +
                "Two wheeler    |    "
        );
        updatedOrder.getVehicles().stream().forEach(vehicle -> {
            vehicleService.printVehicles(vehicle);
        });
        log.info("Order total :" + updatedOrder.getOrderTotal());

        return updatedOrder;
    }
    @Override
    public Order updateOrderByDeletingVehicle(Order order, Vehicle removeVehicle) {
        order.removeItem(removeVehicle);
        Integer orderTotal = order.getVehicles().stream().map(vehicle -> {
                    return (vehicle.getPrice()).intValue();
                }).
                reduce(0, Integer::sum);
        order.setOrderTotal(orderTotal);
        Order updatedOrder = orderDao.updateOrder(order);

        log.info("------ UPDATED ORDERS ------");
        log.info("Name    |     " +
                "Quantity    |     " +
                "Price    |     " +
                "Model no.    |   " +
                "Color     |   " +
                "Fuel type     |   " +
                "Two wheeler    |    "
        );
        updatedOrder.getVehicles().stream().forEach(vehicle -> {
            vehicleService.printVehicles(vehicle);
        });
        log.info("Order total :" + updatedOrder.getOrderTotal());


        return updatedOrder;
    }
    @Override
    public void deleteOrder(Order order) {
        orderDao.deleteOrder(order);
        log.info("Above order is deleted");
    }
    @Override
    public void deleteOrderById(int id) {
        orderDao.deleteOrder(id);
    }
    @Override
    public Order getOrderById(int id) {
        Order order = orderDao.getOrderById(id);
        List<Vehicle> vehicles = order.getVehicles();
        log.info("Name    |     " +
                "Quantity    |     " +
                "Price    |     " +
                "Model no.     |     " +
                "Color       |    " +
                "Fuel type     |   " +
                "Two wheeler    |    "
        );
        vehicles.forEach(vehicle -> {
            String twoWheelerType = vehicle.getTwoWheelerType() != null ? String.valueOf(vehicle.getTwoWheelerType()) : "";
            log.info(vehicle.getVehicleName() + "        " +
                    vehicle.getQuantity() + "        " +
                    vehicle.getPrice() + "        " +
                    vehicle.getVehicleModelNo() + "        " +
                    vehicle.getVehicleColor() + "        " +
                    vehicle.getFuelType() + "        " +
                    twoWheelerType);
        });
        log.info("Order total : " + order.getOrderTotal());
        return order;
    }
    @Override
    public List<Order> getAllOrdersByCustomerNameAndPhoneNo(Customer customer) {
        List<Order> orderList = orderDao.getOrdersByCustomerNameAndPhoneNo(customer.getName(), customer.getPhoneNo());
      log.info("------ ALL ORDERS ------");
        orderList
                .forEach(order -> {
                    log.info("--------- Order id " + order.getId() + "---------");
                    List<Vehicle> vehicles = order.getVehicles();
                    log.info("Name    |     " +
                            "Quantity    |     " +
                            "Price    |     " +
                            "Model no.     |     " +
                            "Color       |    " +
                            "Fuel type     |   " +
                            "Two wheeler    |    "
                    );
                    vehicles.forEach(vehicle -> {
                        String twoWheelerType = vehicle.getTwoWheelerType() != null ? String.valueOf(vehicle.getTwoWheelerType()) : "";
                        log.info(vehicle.getVehicleName() + "        " +
                                vehicle.getQuantity() + "        " +
                                vehicle.getPrice() + "        " +
                                vehicle.getVehicleModelNo() + "        " +
                                vehicle.getVehicleColor() + "        " +
                                vehicle.getFuelType() + "        " +
                                twoWheelerType);
                    });
                    log.info("Order total : " + order.getOrderTotal());
                });
        return orderList;
    }
    public double calculateTotalPriceOfVehicle(Vehicle vehicle) {
        // get the price of vehicle
        double vehiclePrice = vehicle.getPrice();
        // add charges as per color
        double additionalChargesOfColor = vehicle.getVehicleColor().getAdditionalCharges(vehiclePrice);
        // add discount
        double discount = 0;
        switch (vehicle.getVehicleType()) {
            case CAR -> discount = vehicle.getFuelType().getDiscountedPrice(vehiclePrice);
            case BIKE -> discount = vehicle.getTwoWheelerType().getDiscountedPrice(vehiclePrice);
        }
//        System.out.println(discount);
//        System.out.println(additionalChargesOfColor);
//        System.out.println(vehiclePrice);
        vehiclePrice += additionalChargesOfColor;
//        System.out.println(vehiclePrice);
        vehiclePrice -= discount;
        System.out.println(vehiclePrice);
        return vehiclePrice;
    }
}
