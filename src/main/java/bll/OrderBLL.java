package bll;

import DataAccess.OrderDAO;
import Model.OrderBill;

/**
 * Are un camp, de tip OrderDAO, cu care se realizeaza accesul la baza de date, si o singura metoda createOrder, care ia ca parametru parametrii de la metoda createOrderQuery din orderDAO si o apeleaza.
 */
public class OrderBLL {
    OrderDAO orderDAO;
    public OrderBLL() {
        orderDAO = new OrderDAO();
    }
    public void createOrder(int customer_id, int product_id, int price, OrderBill order) {
        orderDAO.createOrderQuery(customer_id, product_id, price, order);

    }
}
