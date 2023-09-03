package com.showroom.Entity;

import com.showroom.constants.Color;
import com.showroom.constants.FuelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "sequence-generator-i")
    @SequenceGenerator(
            name = "sequence-generator-i",
            sequenceName = "invoice_sequence",
            allocationSize = 1
    )
    @Column(name = "invoice_id")
    private int id;

    private double price;

    private Color color;
    private FuelType fuelType;


}
