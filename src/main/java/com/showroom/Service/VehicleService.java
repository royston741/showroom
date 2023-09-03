package com.showroom.Service;

import com.showroom.Entity.Vehicle;
import com.showroom.constants.VehicleType;

import java.util.List;

public interface VehicleService {
    public Vehicle createVehicle(Vehicle vehicle);

    public Vehicle getVehicleById(int id);

    public List<Vehicle> getVehiclesByVehicleType(VehicleType vehicleType);

    public List<Vehicle> getVehicleByVehicleName(String name);

    public List<Vehicle> getAllVehicle();

    public void printVehicles(Vehicle vehicle);
}
