package com.showroom.Entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "sequence-generator")
    @SequenceGenerator(
            name = "sequence-generator",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @Column(name = "customer_id")
    private int id;

    @Column(name = "customer_name")
    @NotNull
    @Length(min = 3,message = "name should be of at least of 3 letters")
    private String name;

    @Column(name = "customer_phone_no")
    @Length(min = 10, max = 10,message = "Phone no should be of length 10")
    private String phoneNo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    public Customer(String name, String phoneNo) {
        this.name = name;
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
