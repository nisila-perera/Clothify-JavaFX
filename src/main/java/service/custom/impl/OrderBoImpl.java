package service.custom.impl;

import entity.OrderEntity;
import model.Order;
import model.OrderDetails;
import org.hibernate.Session;
import service.custom.OrderBo;
import util.HibernateUtil;

import java.util.List;

public class OrderBoImpl implements OrderBo {
    @Override
    public List<Order> getOrder() {
        return List.of();
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
