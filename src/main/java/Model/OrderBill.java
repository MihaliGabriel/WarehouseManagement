package Model;

/**
 * : Reprezinta comenzile aplicatiei. Contine campuri de tip private int.
 * Campurile customer_order si product_order sunt chei straine in baza de date,
 * aceste chei straine se conecteaza de tabelele Customer si Product.
 */
public class OrderBill {
    private int orderID;
    private int customer_order;
    private int product_order;
    private int order_price;

    public OrderBill() {

    }

    public OrderBill(int orderID,int customer_order, int product_order, int order_price) {
        this.orderID = orderID;
        this.customer_order = customer_order;
        this.order_price = order_price;
        this.product_order = product_order;
    }

    public int getProduct_order() {
        return product_order;
    }

    public void setProduct_order(int product_order) {
        this.product_order = product_order;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomer_order() {
        return customer_order;
    }

    public void setCustomer_order(int customer_order) {
        this.customer_order = customer_order;
    }

    public int getOrder_price() {
        return order_price;
    }

    public void setOrder_price(int order_price) {
        this.order_price = order_price;
    }

    @Override
    public String toString() {
        return "OrderBill{" +
                "orderID=" + orderID +
                ", customer_order=" + customer_order +
                ", order_price=" + order_price +
                '}';
    }

}
