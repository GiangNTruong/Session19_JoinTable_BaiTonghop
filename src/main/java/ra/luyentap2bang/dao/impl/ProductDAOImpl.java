package ra.luyentap2bang.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.luyentap2bang.dao.ProductDAO;
import ra.luyentap2bang.entity.Product;

import java.util.List;
@Repository
public class ProductDAOImpl implements ProductDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Product> getProducts() {
        Session session = sessionFactory.openSession();
        try {
            List list = session.createQuery("from Product ").list();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Product getProductById(Integer proId) {
        Session session = sessionFactory.openSession();
        try {
            Product product = session.get(Product.class,proId);
            return product;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean addProduct(Product product) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean deleteProduct(Integer proId) {
       Session session = sessionFactory.openSession();
       try {
           session.beginTransaction();
           session.delete(getProductById(proId));
           session.getTransaction().commit();
       }catch (Exception e){
           e.printStackTrace();
           session.getTransaction().rollback();
       }finally {
           session.close();
       }
       return false;
    }
}
