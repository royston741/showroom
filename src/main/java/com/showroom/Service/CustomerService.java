package com.showroom.Service;

import com.showroom.Entity.Customer;

public interface CustomerService {
    public Customer createCustomer(Customer customer);

    public Customer updateCustomer(Customer customer);

    public void deleteCustomer(Customer customer);

    public Customer getCustomer(Customer customer);

    public Customer findCustomerById(int id);

}
