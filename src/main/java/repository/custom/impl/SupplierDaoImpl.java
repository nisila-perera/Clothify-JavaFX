package repository.custom.impl;

import entity.SupplierEntity;
import org.hibernate.Session;
import repository.custom.SupplierDao;
import util.HibernateUtil;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity supplierEntity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(supplierEntity);
        session.getTransaction().commit();
        session.close();
        return false;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(session.get(SupplierEntity.class,id));
        session.getTransaction().commit();
        return false;
    }

    @Override
    public List<SupplierEntity> getAll() {
        Session session = HibernateUtil.getSession();
        return session.createQuery("from SupplierEntity",SupplierEntity.class).list();
    }

    @Override
    public boolean update(SupplierEntity supplierEntity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(supplierEntity.getId(),supplierEntity);
        session.getTransaction().commit();
        return false;
    }

    @Override
    public SupplierEntity search(String id) {
        Session session = HibernateUtil.getSession();
        return session.get(SupplierEntity.class,id);
    }
}
