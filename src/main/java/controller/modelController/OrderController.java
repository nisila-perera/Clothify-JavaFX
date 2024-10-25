package controller.modelController;

import javafx.scene.chart.PieChart;
import model.Order;
import model.OrderDetails;
import model.Product;
import service.custom.OrderBo;
import service.custom.impl.OrderBoImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderController {

    private static OrderController instance;

    private List<OrderDetails> cart = new ArrayList<>();

    final OrderBo orderservice = new OrderBoImpl();

    double totalprice = 0;

    private OrderController(){};

    public static OrderController getInstance(){
        return instance==null?instance=new OrderController():instance;
    }

    public List<OrderDetails> getCart(){
        return cart;
    }

    public List<OrderDetails> getCart(int i){
        totalprice =0;
        cart = new ArrayList<>();
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

    public String generateId() {
        List<Order> list = orderservice.getOrder();
        if (list.isEmpty()) {
            return "OR1";
        }

        list.sort((order1, order2) -> {
            try {
                String id1Str = order1.getOrid().replace("OR", "");
                String id2Str = order2.getOrid().replace("OR", "");
                int id1 = Integer.parseInt(id1Str);
                int id2 = Integer.parseInt(id2Str);
                return Integer.compare(id1, id2);
            } catch (Exception e) {
                return 0;
            }
        });

        String lastId = list.get(list.size() - 1).getOrid();
        String numberPart = lastId.replace("OR", "");
        int nextId = Integer.parseInt(numberPart) + 1;
        return "OR" + nextId;
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
}
