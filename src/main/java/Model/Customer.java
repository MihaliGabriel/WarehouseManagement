package Model;

/**
 * Reprezinta clientii aplicatiei care vor face comenzi cu anumite produse.
 * Contine metode de getter, getCustomer_id, getCustomer_name, getCustomer_surname, getCustomer_adress,
 * getCustomer_email si metode de setter, setCustomer_id, setCustomer_name, setCustomer_surname,
 * setCustomer_adress si setCustomer_email
 */
public class Customer {
    private int customer_id;
    private String customer_name;
    private String customer_surname;
    private String customer_adress;
    private String customer_email;

    public Customer() {

    }

    public Customer(String customer_name, String customer_surname, String customer_adress, String customer_email) {
        this.customer_name = customer_name;
        this.customer_surname = customer_surname;
        this.customer_adress = customer_adress;
        this.customer_email = customer_email;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_surname() {
        return customer_surname;
    }

    public void setCustomer_surname(String customer_surname) {
        this.customer_surname = customer_surname;
    }

    public String getCustomer_adress() {
        return customer_adress;
    }

    public void setCustomer_adress(String customer_adress) {
        this.customer_adress = customer_adress;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", customer_name='" + customer_name + '\'' +
                ", customer_surname='" + customer_surname + '\'' +
                ", customer_adress='" + customer_adress + '\'' +
                ", customer_email='" + customer_email + '\'' +
                '}';
    }
}
