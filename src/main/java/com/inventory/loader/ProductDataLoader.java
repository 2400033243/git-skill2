package com.inventory.loader;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inventory.entity.Product;

public class ProductDataLoader {

    public static void loadSampleProducts(Session session) {

        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            Product p1 = new Product();
            p1.setName("Laptop");
            p1.setPrice(899.99);
            p1.setQuantity(15);
            p1.setDescription("Electronics");
            session.persist(p1);

            Product p2 = new Product();
            p2.setName("Mouse");
            p2.setPrice(25.50);
            p2.setQuantity(50);
            p2.setDescription("Electronics");
            session.persist(p2);

            Product p3 = new Product();
            p3.setName("Keyboard");
            p3.setPrice(45.00);
            p3.setQuantity(30);
            p3.setDescription("Electronics");
            session.persist(p3);

            Product p4 = new Product();
            p4.setName("Monitor");
            p4.setPrice(299.99);
            p4.setQuantity(20);
            p4.setDescription("Electronics");
            session.persist(p4);

            Product p5 = new Product();
            p5.setName("Desk Chair");
            p5.setPrice(150.00);
            p5.setQuantity(0);
            p5.setDescription("Furniture");
            session.persist(p5);

            Product p6 = new Product();
            p6.setName("Desk Lamp");
            p6.setPrice(35.75);
            p6.setQuantity(25);
            p6.setDescription("Furniture");
            session.persist(p6);

            Product p7 = new Product();
            p7.setName("Notebook");
            p7.setPrice(5.99);
            p7.setQuantity(100);
            p7.setDescription("Stationery");
            session.persist(p7);

            Product p8 = new Product();
            p8.setName("Pen Set");
            p8.setPrice(12.50);
            p8.setQuantity(75);
            p8.setDescription("Stationery");
            session.persist(p8);

            tx.commit();

            System.out.println("Sample products inserted successfully!");

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }

            e.printStackTrace();
        }
    }
}