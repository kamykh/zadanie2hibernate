package pl.edu.wszib.zadanie2hibernate.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wszib.zadanie2hibernate.dao.IProductDAO;
import pl.edu.wszib.zadanie2hibernate.models.Product;

import java.util.List;

public class ProductDAOimpl implements IProductDAO {

    @Autowired
    SessionFactory sessionFactory;

    public ProductDAOimpl(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    @Override
    public void addProduct(Product product) {
        Session session;
        Transaction tx = null;
        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(product);
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public List<Product> showContent() {
        Session session;
        session = this.sessionFactory.openSession();
        List<Product> productList = session.createQuery("SELECT * FROM products").list();
        return productList;
    }

    @Override
    public List<Product> showByAmountLowerThan(int amount) {
        Session session;
        session = this.sessionFactory.openSession();
        List<Product> productList = session.createQuery("SELECT * FROM products WHERE amount < " + amount).list();
        return productList;
    }

    @Override
    public List<Product> showWhatsSold() {
        Session session;
        session = this.sessionFactory.openSession();
        List<Product> productList = session.createQuery("SELECT * FROM products WHERE amount = 0").list();
        return productList;
    }

    @Override
    public List<Product> showAmountsInCategories() {
        Session session;
        session = this.sessionFactory.openSession();
        List<Product> productList = session.createQuery("SELECT category,amount FROM products").list();
        return productList;
    }

    @Override
    public List<Product> showProducers() {
        Session session;
        session = this.sessionFactory.openSession();
        List<Product> productList = session.createQuery("SELECT DISTINCT producer FROM products").list();
        return productList;
    }

   /* @Override
    public void buy(Product product) {
        Session session;
        Transaction tx = null;
        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(product);
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println("No such product");
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
    }*/

    @Override
    public void buy(int id, int amount) {
        Session session;

        session = this.sessionFactory.openSession();
        Product product = (Product) session.createQuery("SELECT * FROM products WHERE id = " + id).uniqueResult();

        if (!(product.getAmount()<amount)) {
            product.setAmount(product.getAmount() - amount);
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.update(product);
                tx.commit();
                session.close();
                System.out.println("Kupiono " + amount + " produktow o id = " + id);
            } catch (HibernateException e) {
                e.printStackTrace();
                if (tx != null) {
                    tx.rollback();
                }
            }
        } else {
            System.out.println("Brak produktu!");
        }
    }
}
