package repository.custom.impl;

import entity.ProductEntity;
import org.hibernate.Session;
import repository.custom.ProductDao;
import util.HibernateUtil;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean save(ProductEntity productEntity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(productEntity);
        session.getTransaction().commit();
        session.close();
        return false;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(session.get(ProductEntity.class,id));
        session.getTransaction().commit();
        return false;
    }

    @Override
    public List<ProductEntity> getAll() {
        Session session = HibernateUtil.getSession();
        return session.createQuery("From ProductEntity", ProductEntity.class).list();
    }

    @Override
    public boolean update(ProductEntity productEntity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(productEntity.getId(),productEntity);
        session.getTransaction().commit();
        return false;
    }

    @Override
    public ProductEntity search(String id) {
        Session session = HibernateUtil.getSession();
        return session.get(ProductEntity.class,id);
    }
}
