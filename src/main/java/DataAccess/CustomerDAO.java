package DataAccess;
import Connection.ConnectionWarehouse;
import Model.*;
import Presentation.TableView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Aceasta clasa extinde clasa AbstractDAO, iar in parametrul T pune Customer.
 *  Aceasta clasa se foloseste de metodele din AbstractDAO, metode generalizate,
 *  pentru a crea metoda mai personalizate pentru datele de tip Customer
 */
public class CustomerDAO extends AbstractDAO<Customer>{

    public void insertQueryCustomer(Customer c) {
        List<String> customerFields = new ArrayList<String>();
        List<Object> customerValues = new ArrayList<Object>();

        List<String> wantedFields = new ArrayList<String>();
        List<Object> wantedValues = new ArrayList<Object>();

        customerFields = AbstractDAO.retrieveProperties(c);
        customerValues = AbstractDAO.retrieveValues(c);


        for(String o : customerFields) {
            if(o.equals("customer_id"))
                continue;
            else wantedFields.add(o);
        }

        for(Object o : customerValues) {
            if(o instanceof Integer)
                continue;
            else wantedValues.add(o);
        }
        System.out.println(wantedValues);
        insertQuery(wantedFields, wantedValues);
    }

    public ResultSet viewQueryCustomer(Customer c) {
        List<String> customerFields = new ArrayList<String>();
        List<Object> customerValues = new ArrayList<Object>();

        List<String> wantedFields = new ArrayList<String>();
        List<Object> wantedValues = new ArrayList<Object>();

        customerFields = AbstractDAO.retrieveProperties(c);
        customerValues = AbstractDAO.retrieveValues(c);
        for(String o : customerFields) {
            if(o.equals("customer_id"))
                continue;
            else wantedFields.add(o);
        }

        for(Object o : customerValues) {
            if(o instanceof Integer)
                continue;
            else wantedValues.add(o);
        }
        ResultSet rs = viewByName(wantedFields, wantedValues);
        return rs;
    }

    public void updateQueryCustomer(Customer c, Customer updatedCustomer) {
        List<String> customerFields = new ArrayList<String>();
        List<Object> customerValues = new ArrayList<Object>();
        List<Object> updatedCustomerValues = new ArrayList<Object>();

        List<String> wantedFields = new ArrayList<String>();
        List<Object> wantedValues = new ArrayList<Object>();
        List<Object> wantedUpdatedValues = new ArrayList<Object>();

        customerFields = AbstractDAO.retrieveProperties(c);
        customerValues = AbstractDAO.retrieveValues(c);
        updatedCustomerValues = AbstractDAO.retrieveValues(updatedCustomer);


        for(String o : customerFields) {
            if(o.equals("customer_id"))
                continue;
            else wantedFields.add(o);
        }

        for(Object o : updatedCustomerValues) {
            if(o instanceof Integer)
                continue;
            else wantedUpdatedValues.add(o);
        }

        for(Object o : customerValues) {
            if(o instanceof Integer)
                continue;
            else wantedValues.add(o);
        }
        System.out.println(wantedUpdatedValues);
        System.out.println(wantedValues);
        updateQuery(wantedFields, wantedFields, wantedValues, wantedUpdatedValues);
    }

    public void deleteQueryCustomer(Customer c) {
        List<String> customerFields = new ArrayList<String>();
        List<Object> customerValues = new ArrayList<Object>();

        List<String> wantedFields = new ArrayList<String>();
        List<Object> wantedValues = new ArrayList<Object>();

        customerFields = AbstractDAO.retrieveProperties(c);
        customerValues = AbstractDAO.retrieveValues(c);
        for(String o : customerFields) {
            if(o.equals("customer_id"))
                continue;
            else wantedFields.add(o);
        }

        for(Object o : customerValues) {
            if(o instanceof Integer)
                continue;
            else wantedValues.add(o);
        }

        deleteQuery(wantedFields, wantedValues);

    }
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<Customer>();

        String queryString = "select * from Customer";
        PreparedStatement statement = null;

        try {
            Connection connection = ConnectionWarehouse.getConnection();
            statement = connection.prepareStatement(queryString);
            ResultSet results = null;
            results = statement.executeQuery();
            customers = createObjects(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
