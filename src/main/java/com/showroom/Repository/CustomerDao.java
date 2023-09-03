package com.showroom.Repository;

import com.showroom.Entity.Customer;
import com.showroom.Entity.Vehicle;
import com.showroom.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerDao {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public int insertCustomer(Customer customer) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        int custId = 0;
        try {
            transaction = session.beginTransaction();
            custId = (int) session.save(customer);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }

        return custId;
    }

    public Customer updateCustomer(Customer customer) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Customer updatedCustomer = null;
        try {
            transaction = session.beginTransaction();
            updatedCustomer = (Customer) session.merge(customer);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return updatedCustomer;
    }

    public Customer getCustomerById(int id) {
        Customer customer = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            customer = session.find(Customer.class, id);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return customer;
    }

    public Customer getCustomerByNameAndPassword(String name, String phoneNo) {
        Customer customer = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            customer = (Customer) session.createQuery("from customer c where c.name=:name AND c.phoneNo=:phoneNo")
                    .setParameter("name", name).setParameter("phoneNo", phoneNo).getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("No customer present");
        } finally {
            session.close();
        }
        return customer;
    }

    public List<Customer> getAllCustomer() {
        List<Customer> customer = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            customer = session.createQuery("Select c from customer c").getResultList();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return customer;
    }

    public void deleteCustomer(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Customer customer = session.find(Customer.class, id);
            if (customer != null)
                session.remove(customer);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
    }

    public void deleteCustomer(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.remove(customer);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
    }
}
