package controller.modelController;

import entity.OrderDetailsEntity;
import entity.OrderEntity;
import entity.ProductEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import javafx.scene.chart.PieChart;
import lombok.Getter;
import lombok.Setter;
import model.Order;
import model.OrderDetails;
import model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.custom.OrderBo;
import service.custom.impl.OrderBoImpl;
import util.HibernateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderController {

    private static OrderController instance;

    private List<OrderDetails> cart = new ArrayList<>();

    final OrderBo orderservice = new OrderBoImpl();

    double totalprice = 0;

    @Setter
    @Getter
    String selectedCustomerName;

    private OrderController(){};

    public static OrderController getInstance(){
        return instance==null?instance=new OrderController():instance;
    }

    public List<OrderDetails> getCart(){
        return cart;
    }

    public List<OrderDetails> getCart(int i){
        totalprice = 0;
        cart = new ArrayList<>();
        selectedCustomerName = null; // Reset customer name
        return cart;
    }

    public List<OrderDetails> getCart(String i){
        return cart;
    }

    public void setTotal(double total){
        totalprice+=total;
    }

    public String getTotal(){
        return totalprice+"";
    }

    public void clearCart(){
        totalprice=0;
    }

    public boolean placeOrder(Order order){
        cart.forEach(orderDetails -> {
            order.setDiscount(orderDetails.getDiscount()+order.getDiscount());
        });
        return orderservice.addOrder(order);
    }

    public boolean deleteOrder(Order order){
        return orderservice.deleteOrder(order);
    }

    public Order searchOrder(String id){
        return orderservice.searchOrder(id);
    }

    public List<Order> getOrder(){
        return orderservice.getOrder();
    }

    public List<Order> getRecentOrders(int limit){
        return orderservice.getRecentOrders(limit);
    }

    public String generateId() {
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT o.orid FROM OrderEntity o ORDER BY o.orid DESC";
            List<String> results = session.createQuery(hql, String.class)
                    .setMaxResults(1)
                    .getResultList();

            if (results.isEmpty()) {
                return "OR1";
            }

            String lastId = results.get(0);
            String numberPart = lastId.replace("OR", "");
            int nextId = Integer.parseInt(numberPart) + 1;
            return "OR" + nextId;
        } catch (Exception e) {
            e.printStackTrace();
            return "OR" + System.currentTimeMillis();
        }
    }

    public void addCart(OrderDetails orderDetails){
        orderservice.addOrderDetails(orderDetails);
    }

    public List<OrderDetails> deleteCart(Order order){
        return orderservice.deleteOrderDetails(order.getOrid());
    }

    public List<OrderDetails> getorderdetails(String id){
        cart = orderservice.searchOrderDetail(id);
        cart.forEach(orderDetails -> {
            setTotal(orderDetails.getPrice());
        });
        return cart;
    }

    public boolean completeOrder(Order order) {
        Session session = null;
        org.hibernate.Transaction transaction = null;

        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();

            OrderEntity orderEntity = new OrderEntity(
                    order.getOrid(),
                    order.getCustname(),
                    order.getCustemail(),
                    order.getPaymenttype(),
                    order.getTotal(),
                    order.getDiscount(),
                    order.getDate(),
                    order.getEmployeeid()
            );
            session.save(orderEntity);

            for (OrderDetails detail : cart) {
                OrderDetailsEntity detailEntity = new OrderDetailsEntity(
                        detail.getId(),
                        detail.getOrid(),
                        detail.getName(),
                        detail.getQty(),
                        detail.getPrice(),
                        detail.getDiscount()
                );
                session.save(detailEntity);

                ProductEntity product = session.get(ProductEntity.class, detail.getId());
                if (product != null) {
                    int newQty = product.getQty() - detail.getQty();
                    if (newQty < 0) {
                        throw new RuntimeException("Insufficient stock for product: " + product.getName());
                    }
                    product.setQty(newQty);
                    session.update(product);
                }
            }

            transaction.commit();

            cart = new ArrayList<>();
            totalprice = 0;
            selectedCustomerName = null;

            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}