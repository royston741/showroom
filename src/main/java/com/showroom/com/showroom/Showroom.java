package com.showroom.com.showroom;

import com.showroom.Entity.Customer;
import com.showroom.Entity.Order;
import com.showroom.Entity.Vehicle;
import com.showroom.Service.CustomerService;
import com.showroom.Service.OrderService;
import com.showroom.Service.VehicleService;
import com.showroom.ServiceImpl.CustomerServiceImpl;
import com.showroom.ServiceImpl.OrderServiceImpl;
import com.showroom.ServiceImpl.VehicleServiceImpl;
import com.showroom.Util.ValidationUtil;
import com.showroom.constants.Color;
import com.showroom.constants.FuelType;
import com.showroom.constants.TwoWheelerType;
import com.showroom.constants.VehicleType;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class Showroom {
    private static final Logger log = Logger.getLogger(Showroom.class);
    private static VehicleService vehicleService = new VehicleServiceImpl();
    private static OrderService orderService = new OrderServiceImpl();
    private static CustomerService customerService = new CustomerServiceImpl();

    public static void main(String[] args) {
//        BasicConfigurator.configure();

        // Create customer
        Customer customer = new Customer();
        customer.setName("Jhon");
        customer.setPhoneNo("6666666666");
        customerService.createCustomer(customer);

        // Get customer
        Customer getCustomer = customerService.getCustomer(customer);
//        log.info(getCustomer);

        // Update customer
        Customer updateCustomer = new Customer();
        getCustomer.setPhoneNo("6666666666"); // change phone no
//        log.info(customerService.updateCustomer(getCustomer));

        // Delete Customer
//        customerService.deleteCustomer(getCustomer);

        // Create scooty order
        Order scootyOrder = new Order();
        Vehicle dioPink = new Vehicle("Dio", 60000d, 6, VehicleType.BIKE, Color.PINK, FuelType.PETROL, TwoWheelerType.SCOOTY, scootyOrder);
        Vehicle dioRed = new Vehicle("Dio", 60000d, 6, VehicleType.BIKE, Color.RED, FuelType.PETROL, TwoWheelerType.SCOOTY, scootyOrder);
        Vehicle dioBlack = new Vehicle("Dio", 60000d, 8, VehicleType.BIKE, Color.BLACK, FuelType.PETROL, TwoWheelerType.SCOOTY, scootyOrder);
        List<Vehicle> vehicles = List.of(dioBlack, dioPink, dioRed); // create list of vehicles
        scootyOrder.setVehicles(vehicles); // set vehicles in order
        scootyOrder.setCustomer(getCustomer); // set customer in order
//        orderService.createOrder(scootyOrder); // create order

        // Create Car order
        Order carOrder = new Order();
        Vehicle hondaCity = new Vehicle("Honda city", 2000000d, 1, VehicleType.CAR, Color.PINK, FuelType.PETROL, null, carOrder);
        carOrder.setVehicles(List.of(hondaCity));
        carOrder.setCustomer(getCustomer);
//        orderService.createOrder(carOrder);
//        orderService.getAllOrdersByCustomerNameAndPhoneNo(customer);

        // Get order by id
//        Order order = orderService.getOrderById(2);
//        orderService.deleteOrder(order);

        // Add vehicle to order
//        dioRed.setOrder(order);
//        orderService.updateOrderByAddingVehicle(order, dioRed);

        // Delete vehicle from order
//        Vehicle deleteVehicle = order.getVehicles().get(1);
//        orderService.updateOrderByDeletingVehicle(order, deleteVehicle);

//        orderService.getAllOrdersByCustomerNameAndPhoneNo(customer);

//        vehicleService.getVehiclesByVehicleType(VehicleType.CAR);
//        vehicleService.getVehicleByVehicleName("Dio");
//    vehicleService.getAllVehicle();
    }
}
