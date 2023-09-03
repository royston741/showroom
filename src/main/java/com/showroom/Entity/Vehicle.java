package com.showroom.Entity;

import com.showroom.constants.Color;
import com.showroom.constants.FuelType;
import com.showroom.constants.TwoWheelerType;
import com.showroom.constants.VehicleType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "sequence-generator-v")
    @SequenceGenerator(
            name = "sequence-generator-v",
            sequenceName = "vehicle_sequence",
            allocationSize = 1
    )
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private int id;

    @Column(name = "vehicle_name")
    private String vehicleName;

    @Column(name = "vehicle_price")
    private Double price;

    @Column(name = "vehicle_model_no")
    private String vehicleModelNo;

    @Column(name = "vehicle_quantity")
    private int quantity;

    @Temporal(TemporalType.DATE)
    @Column(name = "vehicle_manufacture_date")
    private Date manufactureDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type")
    private VehicleType vehicleType;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_color")
    private Color vehicleColor;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_fuel_type")
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    @Column(name = "two_wheeler_type")
    private TwoWheelerType twoWheelerType;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    public Vehicle(String vehicleName, Double price, int quantity, VehicleType vehicleType, Color vehicleColor, FuelType fuelType, TwoWheelerType twoWheelerType, Order order) {
        this.vehicleName = vehicleName;
        this.price = price;
        this.quantity = quantity;
        this.vehicleType = vehicleType;
        this.vehicleColor = vehicleColor;
        this.fuelType = fuelType;
        this.twoWheelerType = twoWheelerType;
        this.order = order;
    }

    @Override
    public String toString() {
        return "\nVehicle{" +
                "\n\t id=" + id +
                ",\n\t vehicleName='" + vehicleName + '\'' +
                ",\n\t price=" + price +
                ",\n\t vehicleModelNo='" + vehicleModelNo + '\'' +
                ",\n\t manufactureDate=" + manufactureDate +
                ",\n\t vehicleType=" + vehicleType +
                ",\n\t vehicleColor=" + vehicleColor +
                ",\n\t fuelType=" + fuelType +
                ",\n\t twoWheelerType=" + twoWheelerType +
                "\n}";
    }
}
