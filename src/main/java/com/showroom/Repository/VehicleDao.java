package com.showroom.Repository;

import com.showroom.Entity.Vehicle;
import com.showroom.Util.HibernateUtil;
import com.showroom.Util.ValidationUtil;
import com.showroom.constants.FuelType;
import com.showroom.constants.Color;
import com.showroom.constants.TwoWheelerType;
import com.showroom.constants.VehicleType;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class VehicleDao {
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public int insertVehicle(Vehicle vehicle) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        int vecId = 0;
        try {
            transaction = session.beginTransaction();
            vecId = (int) session.save(vehicle);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }

        return vecId;
    }

    public Vehicle getVehicleById(int id) {
        Vehicle vehicle = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            vehicle = session.find(Vehicle.class, id);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return vehicle;
    }

    public List<Vehicle> getAllVehicleByVehicleName(String name) {
        List<Vehicle> vehicle = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            vehicle =  session.createQuery("from vehicle v where v.vehicleName=:name").setParameter("name", name).getResultList();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return vehicle;
    }

    public List<Vehicle> getVehiclesByVehicleType(VehicleType vehicleType) {
        List<Vehicle> vehicle = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from vehicle v where v.vehicleType=:type");
            query.setParameter("type", vehicleType);
            vehicle = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return vehicle;
    }

    public List<Vehicle> getAllVehicle() {
        List<Vehicle> vehicle = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            vehicle = session.createQuery("Select v from vehicle v").getResultList();

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return vehicle;
    }

    public void deleteVehicle(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Vehicle vehicle = session.find(Vehicle.class, id);
            if (vehicle != null)
                session.remove(vehicle);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
    }
}
