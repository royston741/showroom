package com.showroom.ServiceImpl;

import com.showroom.Entity.Customer;
import com.showroom.Repository.CustomerDao;
import com.showroom.Service.CustomerService;
import com.showroom.Util.ValidationUtil;
import org.apache.log4j.Logger;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao = new CustomerDao();
    private static final Logger log = Logger.getLogger(ValidationUtil.class);


    @Override
    public Customer createCustomer(Customer customer) {
        Customer existingCustomer = customerDao.getCustomerByNameAndPassword(customer.getName(), customer.getPhoneNo());
        if (existingCustomer == null) {
            int customerId = customerDao.insertCustomer(customer);
            existingCustomer = customerDao.getCustomerById(customerId);
        }
        return existingCustomer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerDao.deleteCustomer(customer);
    }

    @Override
    public Customer getCustomer(Customer customer) {
        Customer existingCustomer = customerDao.getCustomerByNameAndPassword(customer.getName(), customer.getPhoneNo());
        return existingCustomer;
    }

    @Override
    public Customer findCustomerById(int id) {
        return customerDao.getCustomerById(id);
    }



}
