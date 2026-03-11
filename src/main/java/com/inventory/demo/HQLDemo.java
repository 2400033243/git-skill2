package com.inventory.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.inventory.entity.Product;
import com.inventory.loader.ProductDataLoader;
import com.inventory.util.HibernateUtil;

public class HQLDemo {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {

            // Load sample products (Run once)
            ProductDataLoader.loadSampleProducts(session);

            sortProductsByPriceAscending(session);
            sortProductsByPriceDescending(session);
            sortProductsByQuantity(session);

            getFirstThreeProducts(session);
            getNextThreeProducts(session);

            countTotalProducts(session);
            countProductsInStock(session);

            findMinMaxPrice(session);

            groupProductsByDescription(session);

            filterProductsByPriceRange(session, 20, 100);

            findProductsStartingWith(session, "D");
            findProductsEndingWith(session, "p");
            findProductsContaining(session, "Desk");

        } finally {

            session.close();
            factory.close();
        }
    }

    // Sort by price ASC
    public static void sortProductsByPriceAscending(Session session) {

        String hql = "FROM Product p ORDER BY p.price ASC";

        Query<Product> query = session.createQuery(hql, Product.class);
        List<Product> list = query.list();

        System.out.println("\nProducts Sorted by Price ASC");

        for (Product p : list) {
            System.out.println(p);
        }
    }

    // Sort by price DESC
    public static void sortProductsByPriceDescending(Session session) {

        String hql = "FROM Product p ORDER BY p.price DESC";

        Query<Product> query = session.createQuery(hql, Product.class);
        List<Product> list = query.list();

        System.out.println("\nProducts Sorted by Price DESC");

        for (Product p : list) {
            System.out.println(p);
        }
    }

    // Sort by quantity
    public static void sortProductsByQuantity(Session session) {

        String hql = "FROM Product p ORDER BY p.quantity DESC";

        Query<Product> query = session.createQuery(hql, Product.class);
        List<Product> list = query.list();

        System.out.println("\nProducts Sorted by Quantity");

        for (Product p : list) {
            System.out.println(p.getName() + " - " + p.getQuantity());
        }
    }

    // Pagination First 3
    public static void getFirstThreeProducts(Session session) {

        String hql = "FROM Product";

        Query<Product> query = session.createQuery(hql, Product.class);

        query.setFirstResult(0);
        query.setMaxResults(3);

        List<Product> list = query.list();

        System.out.println("\nFirst 3 Products");

        for (Product p : list) {
            System.out.println(p);
        }
    }

    // Pagination Next 3
    public static void getNextThreeProducts(Session session) {

        String hql = "FROM Product";

        Query<Product> query = session.createQuery(hql, Product.class);

        query.setFirstResult(3);
        query.setMaxResults(3);

        List<Product> list = query.list();

        System.out.println("\nNext 3 Products");

        for (Product p : list) {
            System.out.println(p);
        }
    }

    // Count products
    public static void countTotalProducts(Session session) {

        String hql = "SELECT COUNT(p) FROM Product p";

        Query<Long> query = session.createQuery(hql, Long.class);

        Long count = query.uniqueResult();

        System.out.println("\nTotal Products: " + count);
    }

    // Count products in stock
    public static void countProductsInStock(Session session) {

        String hql = "SELECT COUNT(p) FROM Product p WHERE p.quantity > 0";

        Query<Long> query = session.createQuery(hql, Long.class);

        Long count = query.uniqueResult();

        System.out.println("\nProducts In Stock: " + count);
    }

    // Min and Max price
    public static void findMinMaxPrice(Session session) {

        String hql = "SELECT MIN(p.price), MAX(p.price) FROM Product p";

        Query<Object[]> query = session.createQuery(hql, Object[].class);

        Object[] result = query.uniqueResult();

        System.out.println("\nMinimum Price: " + result[0]);
        System.out.println("Maximum Price: " + result[1]);
    }

    // Group by description
    public static void groupProductsByDescription(Session session) {

        String hql = "SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description";

        Query<Object[]> query = session.createQuery(hql, Object[].class);

        List<Object[]> list = query.list();

        System.out.println("\nProducts Grouped By Description");

        for (Object[] obj : list) {
            System.out.println(obj[0] + " : " + obj[1]);
        }
    }

    // Price range filter
    public static void filterProductsByPriceRange(Session session, double min, double max) {

        String hql = "FROM Product p WHERE p.price BETWEEN :min AND :max";

        Query<Product> query = session.createQuery(hql, Product.class);

        query.setParameter("min", min);
        query.setParameter("max", max);

        List<Product> list = query.list();

        System.out.println("\nProducts between " + min + " and " + max);

        for (Product p : list) {
            System.out.println(p.getName() + " - " + p.getPrice());
        }
    }

    // Names starting with letter
    public static void findProductsStartingWith(Session session, String prefix) {

        String hql = "FROM Product p WHERE p.name LIKE :pattern";

        Query<Product> query = session.createQuery(hql, Product.class);

        query.setParameter("pattern", prefix + "%");

        List<Product> list = query.list();

        System.out.println("\nProducts starting with " + prefix);

        for (Product p : list) {
            System.out.println(p.getName());
        }
    }

    // Names ending with letter
    public static void findProductsEndingWith(Session session, String suffix) {

        String hql = "FROM Product p WHERE p.name LIKE :pattern";

        Query<Product> query = session.createQuery(hql, Product.class);

        query.setParameter("pattern", "%" + suffix);

        List<Product> list = query.list();

        System.out.println("\nProducts ending with " + suffix);

        for (Product p : list) {
            System.out.println(p.getName());
        }
    }

    // Names containing word
    public static void findProductsContaining(Session session, String text) {

        String hql = "FROM Product p WHERE p.name LIKE :pattern";

        Query<Product> query = session.createQuery(hql, Product.class);

        query.setParameter("pattern", "%" + text + "%");

        List<Product> list = query.list();

        System.out.println("\nProducts containing " + text);

        for (Product p : list) {
            System.out.println(p.getName());
        }
    }
}