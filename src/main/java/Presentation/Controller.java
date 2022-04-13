package Presentation;

import java.awt.event.*;
import java.sql.CallableStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;

import Connection.*;
import Model.*;
import bll.*;
import java.io.*;

import javax.swing.*;
import DataAccess.*;

/**
 * Din clasa Controller se va porni aplicatia.
 * Aceasta are ca si campuri toate interfetele, clasele din pachetul bll,
 * din Model si conexiunea la baza de date.
 */
public class Controller {
    private MainView main;

    private AddProductView productView;
    private AddCustomerView customerView;
    private EditProductView editProductView;
    private EditCustomerView editCustomerView;
    private CustomerDetails customerDetails;
    private ProductDetails productDetails;
    private EditSaveCustomerView editSaveCustomerView;
    private EditSaveProductView editSaveProductView;
    private TableView tableView1;
    private TableView tableView2;
    private OrderView orderView;

    private CustomerBLL customerBLL;
    private ProductBLL productBLL;
    private OrderBLL orderBLL;
    private int orderNo = 0;

    private Connection connection = null;
    private Customer selectedCustomer;
    private Product selectedProduct;

    public Controller(MainView main) {
        this.main = main;
        orderBLL = new OrderBLL();
        customerBLL = new CustomerBLL();
        productBLL = new ProductBLL();
        selectedCustomer = new Customer();
        selectedProduct = new Product();
        main.addClientListener(new ClientListener());
        main.addProdusListener(new ProdusListener());
        main.addEditListener(new EditProdusListener());
        main.addEditCustomerListener(new EditCustomerListener());
        main.addTableClientListener(new TableClientListener());
        main.addTableProductListener(new TableProductListener());
        main.addComandaListener(new ComandaListener());
        try {
            connection = ConnectionWarehouse.getConnection();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public class ComandaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
          orderView = new OrderView();
          orderView.addListenerAddOrder(new AddOrderListener());
            List<Customer> customers = new ArrayList<Customer>();
            customers = customerBLL.getCustomers();
            for(Customer c : customers) {
                orderView.addList(c.getCustomer_surname() + " " + c.getCustomer_name() + " " + c.getCustomer_email() + " " + c.getCustomer_adress());
            }
            List<Product> products = new ArrayList<Product>();
            products = productBLL.getProducts();
            for(Product p : products) {
                orderView.addList2(p.getProduct_name() + " " + p.getProduct_price() + " " + p.getProduct_stock());
            }
        }
    }
    public class AddOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int cantitate = Integer.valueOf(orderView.getCantitate());
            OrderBill order = new OrderBill();
            String[] fieldss = orderView.getValue2().split(" ");
            List<String> values = new ArrayList<String>();
            ResultSet rs = null;
            for(int i = 0; i < fieldss.length; i++) {
                values.add(fieldss[i]);
            }
            Product p = new Product(values.get(0), Integer.valueOf(values.get(1)), Integer.valueOf(values.get(2)));
            System.out.println(p.toString());
            String[] fieldsCustomer = orderView.getValue1().split(" ");
            List<String> valuesCustomer = new ArrayList<String>();
            for(int i = 0; i < fieldsCustomer.length; i++) {
                valuesCustomer.add(fieldsCustomer[i]);
            }
            Customer c = new Customer(valuesCustomer.get(1), valuesCustomer.get(0), valuesCustomer.get(3), valuesCustomer.get(2));
            Product updatedP;
            System.out.println(c.toString());
            if(cantitate > p.getProduct_stock()) {
                JOptionPane.showMessageDialog(null,"Prea putine produse pe stoc", "Info", JOptionPane.ERROR_MESSAGE);
            }
            else {
                updatedP = new Product(values.get(0), Integer.valueOf(values.get(1)), Integer.valueOf(values.get(2)) - cantitate);
                int price = Integer.valueOf(orderView.getCantitate()) * p.getProduct_price();
                orderView.setPret(String.valueOf(price));
                ResultSet rsClient = null;
                ResultSet rsProduct = null;
                int id_client = 0;
                String nume_client = new String();
                String prenume_client = new String();
                int id_product = 0;
                String nume_produs = new String();
                rsClient = customerBLL.viewCustomer(c);
                rsProduct = productBLL.viewProduct(p);
                try {
                    while(rsClient.next()) {
                        id_client = rsClient.getInt(1);
                        nume_client = rsClient.getString(2);
                        prenume_client = rsClient.getString(3);
                    }
                    while(rsProduct.next()) {
                        id_product = rsProduct.getInt(1);
                        nume_produs = rsProduct.getString(2);
                    }
                }catch(SQLException x) {
                    x.printStackTrace();
                }
                System.out.println(id_client +" " + id_product);
                orderBLL.createOrder(id_client, id_product, price, order);
                productBLL.updateProduct(p, updatedP);
                orderNo++;
                File file = new File("Order" + orderNo);
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.append("Comanda :" + orderNo +"\n"+ "Client : " + nume_client +" "+ prenume_client + "\n"+"Produs : " + nume_produs +"\n"+"Pret produs : " +String.valueOf(p.getProduct_price())  + "\n"+ "Cantitate : " + cantitate +"\n"+ "Pret total comanda :" + price);
                    fileWriter.close();

                }catch(IOException z) {
                    z.printStackTrace();
                }

            }

        }
    }
    public class TableProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Product d = new Product();
            List<String> properties = new ArrayList<String>();
            List<Object> valuesc = new ArrayList<Object>();
            List<Product> products = new ArrayList<Product>();

            properties = AbstractDAO.retrieveProperties(d);
            products = productBLL.getProducts();

            Object[][] data = new Object[products.size()][properties.size()];
            Object[] columns = new Object[properties.size()];

            for(int z = 0; z < columns.length; z++) {
                columns[z] = properties.get(z);
            }

            for(int x = 0; x < data.length; x++) {
                Product c = products.get(x);
                valuesc = AbstractDAO.retrieveValues(c);
                for(int y = 0; y < data[0].length; y++) {
                    data[x][y] = valuesc.get(y);
                }
            }
            TableView tableView1 = new TableView(columns, data);
        }
    }
    public class TableClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Customer d = new Customer();
            List<String> properties = new ArrayList<String>();
            List<Object> valuesc = new ArrayList<Object>();
            List<Customer> customers = new ArrayList<Customer>();

            properties = AbstractDAO.retrieveProperties(d);
            customers = customerBLL.getCustomers();

            Object[][] data = new Object[customers.size()][properties.size()];
            Object[] columns = new Object[properties.size()];

            for(int z = 0; z < columns.length; z++) {
                columns[z] = properties.get(z);
            }

            for(int x = 0; x < data.length; x++) {
                Customer c = customers.get(x);
                valuesc = AbstractDAO.retrieveValues(c);
                for(int y = 0; y < data[0].length; y++) {
                    data[x][y] = valuesc.get(y);
                }
            }
            TableView tableView1 = new TableView(columns, data);
        }
    }
    public class DetailsProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            productDetails = new ProductDetails();
            String[] fieldss = editProductView.getItem().split(" ");
            List<String> values = new ArrayList<String>();
            ResultSet rs = null;
            for(int i = 0; i < fieldss.length; i++) {
                values.add(fieldss[i]);
            }
            Product c = new Product(values.get(0), Integer.valueOf(values.get(1)), Integer.valueOf(values.get(2)));
            selectedProduct = c;
            System.out.println(selectedProduct.toString());
            rs = productBLL.viewProduct(c);
            try {
                while(rs.next()) {
                    productDetails.setNumetext(rs.getString(2));
                    productDetails.setPrettext(String.valueOf(rs.getInt(3)));
                    productDetails.setStoctext(String.valueOf(rs.getInt(4)));
                }
            }catch(SQLException a) {
                a.printStackTrace();
            }
        }
    }
    public class DetailsCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            customerDetails = new CustomerDetails();
            String[] fieldss = editCustomerView.getItem().split(" ");
            List<String> values = new ArrayList<String>();
            ResultSet rs = null;
            for(int i = 0; i < fieldss.length; i++) {
                values.add(fieldss[i]);
            }
            Customer c = new Customer(values.get(1), values.get(0), values.get(3), values.get(2));
            System.out.println(c.toString());
            rs = customerBLL.viewCustomer(c);
            try {
                while(rs.next()) {
                    customerDetails.setPrenumetext(rs.getString(2));
                    customerDetails.setNumetext(rs.getString(3));
                    customerDetails.setEmailtext(rs.getString(5));
                    customerDetails.setAdresatext(rs.getString(4));
                }
            }catch(SQLException a) {
                a.printStackTrace();
            }
        }
    }
    public class EditCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            editCustomerView = new EditCustomerView();
            editCustomerView.addListenerDetailsCustomer(new DetailsCustomerListener());
            editCustomerView.addListenerEditCustomer(new EditSaveCustomerListener());
            editCustomerView.addListenerDeleteCustomer(new DeleteCustomerListener());
            List<Customer> customers = new ArrayList<Customer>();
            customers = customerBLL.getCustomers();
            for(Customer c : customers) {
                editCustomerView.addList(c.getCustomer_surname() + " " + c.getCustomer_name() + " " + c.getCustomer_email() + " " + c.getCustomer_adress());
                }
        }
    }
    public class DeleteCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] fieldss = editCustomerView.getItem().split(" ");
            List<String> values = new ArrayList<String>();
            ResultSet rs = null;
            for(int i = 0; i < fieldss.length; i++) {
                values.add(fieldss[i]);
            }
            Customer c = new Customer(values.get(1), values.get(0), values.get(3), values.get(2));
            System.out.println(c.toString());
            customerBLL.deleteCustomer(c);
            JOptionPane.showMessageDialog(null,"Client sters cu succes!", "Info", JOptionPane.INFORMATION_MESSAGE);
            editCustomerView.closeView();
        }
    }
    public class EditSaveCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String[] fieldss = editCustomerView.getItem().split(" ");
            List<String> values = new ArrayList<String>();
            ResultSet rs = null;
            for(int i = 0; i < fieldss.length; i++) {
                values.add(fieldss[i]);
            }
            Customer c = new Customer(values.get(1), values.get(0), values.get(3), values.get(2));
            System.out.println(c.toString());
            editSaveCustomerView = new EditSaveCustomerView(c);
            editSaveCustomerView.saveCustomer(new SaveCustomerListener());
    }

    public class SaveCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Customer customer = editSaveCustomerView.customerUpdate;
            String nume = editSaveCustomerView.getNumetext();
            String prenume = editSaveCustomerView.getPrenumetext();
            String adresa = editSaveCustomerView.getAdresatext();
            String email = editSaveCustomerView.getEmailtext();

            if(nume.isEmpty() || prenume.isEmpty() || adresa.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Date introduse incorect!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                Customer updatedCustomer = new Customer(prenume, nume, adresa, email);
                customerBLL.updateCustomer(customer, updatedCustomer);
                JOptionPane.showMessageDialog(null,"Client modificat cu succes!", "Info", JOptionPane.INFORMATION_MESSAGE);
                editCustomerView.closeView();
            }
        }
        }
    }
    public class EditProdusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            editProductView = new EditProductView();
            editProductView.addListenerDetailsProduct(new DetailsProductListener());
            editProductView.addListenerEditProduct(new EditSaveProductListener());
            editProductView.addListenerDeleteProduct(new DeleteProductListener());
            List<Product> products = new ArrayList<Product>();
            products = productBLL.getProducts();
            for(Product p : products) {
                editProductView.addList(p.getProduct_name() + " " + p.getProduct_price() + " " + p.getProduct_stock());
            }
        }
    }

    public class DeleteProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] fieldss = editProductView.getItem().split(" ");
            List<String> values = new ArrayList<String>();
            ResultSet rs = null;
            for(int i = 0; i < fieldss.length; i++) {
                values.add(fieldss[i]);
            }
            Product c = new Product(values.get(0), Integer.valueOf(values.get(1)), Integer.valueOf(values.get(2)));
            productBLL.deleteProduct(c);
            JOptionPane.showMessageDialog(null,"Produs sters cu succes!", "Info", JOptionPane.INFORMATION_MESSAGE);
            editProductView.closeView();

        }
    }
    public class EditSaveProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] fieldss = editProductView.getItem().split(" ");
            List<String> values = new ArrayList<String>();
            ResultSet rs = null;
            for(int i = 0; i < fieldss.length; i++) {
                values.add(fieldss[i]);
            }
            Product c = new Product(values.get(0), Integer.valueOf(values.get(1)), Integer.valueOf(values.get(2)));
            editSaveProductView = new EditSaveProductView(c);
            editSaveProductView.saveProduct(new SaveProductListener());
        }
    }

    public class SaveProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Product product = editSaveProductView.productUpdate;
            String nume = editSaveProductView.getNumetext();
            if(nume.isEmpty() || editSaveProductView.getStoctext().isEmpty() || editSaveProductView.getPrettext().isEmpty()) {
                JOptionPane.showMessageDialog(null,"Date introduse incorect!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                int stoc = Integer.valueOf(editSaveProductView.getStoctext());
                int pret = Integer.valueOf(editSaveProductView.getPrettext());
                Product updatedProduct = new Product(nume, pret, stoc);
                System.out.println(product.toString());
                productBLL.updateProduct(product, updatedProduct);
                JOptionPane.showMessageDialog(null,"Produs modificat cu succes!", "Info", JOptionPane.INFORMATION_MESSAGE);
                editSaveProductView.closeView();
            }

        }
    }
    public class ClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            customerView = new AddCustomerView();
            customerView.createCustomer(new CreateCustomerListener());
        }
    }

    public class ProdusListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            productView = new AddProductView();
            productView.createProduct(new CreateProductListener());
        }
    }

    public class CreateCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nume = customerView.getNumetext();
            String prenume = customerView.getPrenumetext();
            String adresa = customerView.getAdresatext();
            String email = customerView.getEmailtext();
            if(nume.isEmpty() || prenume.isEmpty() || adresa.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Date introduse incorect!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                Customer c = new Customer(prenume, nume, adresa, email);
                customerBLL.insertCustomer(c);
                JOptionPane.showMessageDialog(null,"Client creat cu succes!", "Info", JOptionPane.INFORMATION_MESSAGE);
                customerView.closeView();
            }
        }
    }

    public class CreateProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nume = productView.getNumetext();
            String pret = productView.getPrettext();
            String stoc = productView.getStoctext();
            if(nume.isEmpty() || pret.isEmpty() || stoc.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Date introduse incorect!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                Product p = new Product(nume, Integer.valueOf(pret), Integer.valueOf(stoc));
                productBLL.insertProduct(p);
                JOptionPane.showMessageDialog(null,"Produs creat cu succes!", "Info", JOptionPane.INFORMATION_MESSAGE);
                productView.closeView();
            }
        }
    }
    public static void main(String[] args) {
        MainView view = new MainView();
        Controller controller = new Controller(view);
    }
}
