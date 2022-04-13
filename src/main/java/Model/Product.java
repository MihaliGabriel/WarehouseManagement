package Model;

/**
 * Reprezinta produsele aplicatiei care vor fi comandate de diversi clienti. Are 2 constructori,
 * un constructor null si un constructor care are ca parametri anumite campuri.
 * Are campurile nume, pret, stoc si id.
 */
public class Product {
    private int product_id;
    private String product_name;
    private int product_price;
    private int product_stock;

    public Product() {

    }

    public Product(String product_name, int product_price, int product_stock) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_stock = product_stock;
    }

    public int getProduct_stock() {
        return product_stock;
    }

    public void setProduct_stock(int product_stock) {
        this.product_stock = product_stock;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_price=" + product_price +"}";
    }

}
