package bll;
import DataAccess.AbstractDAO;
import DataAccess.CustomerDAO;
import DataAccess.ProductDAO;
import Model.Customer;
import Validators.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Are 2 campuri, unul de tip Validator, cu care se valideaza datele despre un Customer introduse, si unul de tip CustomerDAO, cu care se realizeaza accesul la baza de date. Contine metodele insertCustomer(), viewCustomer(), deleteCustomer(), updateCustomer() si getCustomer() care primesc parametri de tip Customer si apeleaza metodele din customerDAO.
 */
public class CustomerBLL {
    private Validator<Customer> validator;
    private CustomerDAO customerDAO;


    public CustomerBLL() {
        validator = new EmailValidator();
        customerDAO = new CustomerDAO();

    }

    public void insertCustomer(Customer customer) {
        validator.validate(customer);
        customerDAO.insertQueryCustomer(customer);

    }

     public ResultSet viewCustomer(Customer customer) {
        ResultSet rs = null;
        validator.validate(customer);
        rs = customerDAO.viewQueryCustomer(customer);
        return rs;
    }

    public void updateCustomer(Customer customer, Customer updatedCustomer) {
        validator.validate(updatedCustomer);
        customerDAO.updateQueryCustomer(customer, updatedCustomer);
    }

    public void deleteCustomer(Customer customer) {
        customerDAO.deleteQueryCustomer(customer);
    }
    public List<Customer> getCustomers() {
        return customerDAO.getAllCustomers();
    }

}
