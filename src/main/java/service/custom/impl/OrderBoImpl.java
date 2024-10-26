package service.custom.impl;

import entity.OrderEntity;
import model.Order;
import model.OrderDetails;
import org.hibernate.Session;
import org.hibernate.query.Query;
import service.custom.OrderBo;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class OrderBoImpl implements OrderBo {
    @Override
    public List<Order> getOrder() {
        Session session = HibernateUtil.getSession();
        try {
            String hql = "FROM OrderEntity ORDER BY date DESC";
            Query query = session.createQuery(hql);
            List<OrderEntity> orderEntities = query.getResultList();

            List<Order> orders = new ArrayList<>();
            for (OrderEntity entity : orderEntities) {
                orders.add(new Order(
                        entity.getOrid(),
                        entity.getCustname(),
                        entity.getCustemail(),
                        entity.getPaymenttype(),
                        entity.getTotal(),
                        entity.getDiscount(),
                        entity.getDate(),
                        entity.getEmployeeid()
                ));
            }
            return orders;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Order> getRecentOrders(int limit) {
        Session session = HibernateUtil.getSession();
        try {
            String hql = "FROM OrderEntity ORDER BY date DESC, orid DESC";
            Query query = session.createQuery(hql);
            query.setMaxResults(limit);
            List<OrderEntity> orderEntities = query.getResultList();

            List<Order> orders = new ArrayList<>();
            for (OrderEntity entity : orderEntities) {
                orders.add(new Order(
                        entity.getOrid(),
                        entity.getCustname(),
                        entity.getCustemail(),
                        entity.getPaymenttype(),
                        entity.getTotal(),
                        entity.getDiscount(),
                        entity.getDate(),
                        entity.getEmployeeid()
                ));
            }
            return orders;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public boolean addOrder(Order order) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(order);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean deleteOrder(Order order) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(searchdetails(order.getOrid()));
        session.getTransaction().commit();
        return false;
    }

    @Override
    public Order searchOrder(String id) {
        return null;
    }

    @Override
    public boolean addOrderDetails(OrderDetails orderDetails) {
        return false;
    }

    @Override
    public List<OrderDetails> deleteOrderDetails(String id) {
        return List.of();
    }

    @Override
    public List<OrderDetails> searchOrderDetail(String id) {
        return List.of();
    }

    public OrderEntity searchdetails(String id) {
        Session session = HibernateUtil.getSession();
        return session.get(OrderEntity.class,id);
    }
}
