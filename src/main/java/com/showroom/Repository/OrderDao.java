package com.showroom.Repository;


import com.showroom.Entity.Customer;
import com.showroom.Entity.Order;
import com.showroom.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class OrderDao {
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Order insertOrder(Order order) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Order insertedOrder = null;
        int orderId = 0;
        try {
            transaction = session.beginTransaction();
            orderId = (int) session.save(order);
            insertedOrder = getOrderById(orderId);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }

        return insertedOrder;
    }

    public List<Order> getOrdersByCustomerNameAndPhoneNo(String name, String phoneNo) {
        List<Order> orders = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            orders = session.createQuery("Select o from Order o where o.customer.name=:name AND o.customer.phoneNo=:phoneNo")
                    .setParameter("name", name).setParameter("phoneNo", phoneNo).getResultList();
//            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return orders;
    }

    public Order getOrdersByOrderId(String name, String phoneNo, int orderId) {
        Order order = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("Select o from Order o where o.customer.name=:name AND o.customer.phoneNo=:phoneNo AND o.id=:orderId")
                    .setParameter("name", name).setParameter("phoneNo", phoneNo).setParameter("orderId", orderId);
            order = (Order) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return order;
    }

    public Order getOrderById(int id) {
        Order order = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            order = session.find(Order.class, id);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return order;
    }

    public void deleteOrder(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Order order = session.find(Order.class, id);
            session.remove(order);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
    }

    public void deleteOrder(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.remove(order);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
    }

    public Order updateOrder(Order order) {
        Session session = sessionFactory.openSession();
        Order updatedOrder=null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
          order= (Order) session.merge(order);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return order;
    }

}
