package com.showroom.ServiceImpl;

import com.showroom.Entity.Customer;
import com.showroom.Entity.Order;
import com.showroom.Entity.Vehicle;
import com.showroom.Repository.VehicleDao;
import com.showroom.Service.CustomerService;
import com.showroom.Service.VehicleService;
import com.showroom.Util.ValidationUtil;
import com.showroom.constants.Color;
import com.showroom.constants.FuelType;
import com.showroom.constants.TwoWheelerType;
import com.showroom.constants.VehicleType;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleServiceImpl implements VehicleService {

    private final Logger log = Logger.getLogger(VehicleServiceImpl.class);
    VehicleDao vehicleDao = new VehicleDao();

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        vehicle.setVehicleModelNo(vehicle.getVehicleName().split(" ")[0] + "1234");
        vehicle.setManufactureDate(new Date());
        double newPrice = new OrderServiceImpl().calculateTotalPriceOfVehicle(vehicle);
        newPrice *= vehicle.getQuantity();
        vehicle.setPrice(newPrice);
        return vehicle;
    }

    @Override
    public Vehicle getVehicleById(int id) {
        return vehicleDao.getVehicleById(id);
    }

    @Override
    public List<Vehicle> getVehicleByVehicleName(String name) {
        List<Vehicle> vehicles = null;
        // if vehicle type selected
        if (name != null) {
            vehicles = vehicleDao.getAllVehicleByVehicleName(name);
            log.info("Name    |     " +
                    "Quantity    |     " +
                    "Price    |     " +
                    "Model no.     |     " +
                    "Color       |    " +
                    "Fuel type     |   " +
                    "Two wheeler    |    "
            );
            vehicles.forEach(vehicle -> printVehicles(vehicle));
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getVehiclesByVehicleType(VehicleType vehicleType) {
        List<Vehicle> vehicles = null;
        // if vehicle type selected
        if (vehicleType != null) {
            vehicles = vehicleDao.getVehiclesByVehicleType(vehicleType);
            log.info("Name    |     " +
                    "Quantity    |     " +
                    "Price    |     " +
                    "Model no.     |     " +
                    "Color       |    " +
                    "Fuel type     |   " +
                    "Two wheeler    |    "
            );
            vehicles.forEach(vehicle -> printVehicles(vehicle));
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getAllVehicle() {
        List<Vehicle> vehicles = vehicleDao.getAllVehicle();
        log.info("Name    |     " +
                "Quantity    |     " +
                "Price    |     " +
                "Model no.     |     " +
                "Color       |    " +
                "Fuel type     |   " +
                "Two wheeler    |    "
        );
        vehicles.forEach(vehicle -> printVehicles(vehicle));
        return vehicles;
    }

    @Override
    public void printVehicles(Vehicle vehicle) {
        String twoWheelerType = vehicle.getTwoWheelerType() != null ? String.valueOf(vehicle.getTwoWheelerType()) : "";
        log.info(vehicle.getVehicleName() + "        " +
                vehicle.getQuantity() + "        " +
                vehicle.getPrice() + "        " +
                vehicle.getVehicleModelNo() + "        " +
                vehicle.getVehicleColor() + "        " +
                vehicle.getFuelType() + "        " +
                twoWheelerType);
    }

}
