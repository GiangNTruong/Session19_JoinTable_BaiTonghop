package ra.luyentap2bang.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.luyentap2bang.dao.CategoryDAO;
import ra.luyentap2bang.entity.Category;

import java.util.List;
@Repository
public class CategoryDAOImpl implements CategoryDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Category> getCategories() {
        Session session = sessionFactory.openSession();
        try {
            List list = session.createQuery("from  Category ").list();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Category getCategoryById(String catId) {
        Session session = sessionFactory.openSession();
        try {
            Category category = session.get(Category.class,catId);
            return category;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean addCategory(Category category) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean updateCategory(Category category) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(category);
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
    public boolean deleteCategory(String catId) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(getCategoryById(catId));
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
