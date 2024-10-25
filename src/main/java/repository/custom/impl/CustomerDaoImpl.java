package repository.custom.impl;

import entity.CustomerEntity;
import entity.EmployeeEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.custom.CustomerDao;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(CustomerEntity customerEntity) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(customerEntity);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
                return false;
            }
        }

    }

    @Override
    public boolean delete(String id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                CustomerEntity customer = session.get(CustomerEntity.class, id);
                if (customer != null) {
                    session.remove(customer);
                    transaction.commit();
                    return true;
                }
                return false;
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public List<CustomerEntity> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            Query<CustomerEntity> query = session.createQuery("FROM CustomerEntity", CustomerEntity.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean update(CustomerEntity customerEntity) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(customerEntity);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public CustomerEntity search(String id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(CustomerEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
