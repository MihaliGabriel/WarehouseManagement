package DataAccess;
import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Connection.*;

/**
 * Ca si OrderDAO, extinde clasa AbstractDAO, iar in T se pune Product.
 * Are mai multe metode personalizate, asemanatoare cu cele din CustomerDAO, cu mici diferente.
 */
public class ProductDAO  extends AbstractDAO<Product>{

    public void insertQueryProduct(Product c) {
        List<String> productFields = new ArrayList<String>();
        List<Object> productValues = new ArrayList<Object>();

        List<String> wantedFields = new ArrayList<String>();
        List<Object> wantedValues = new ArrayList<Object>();

        productFields = AbstractDAO.retrieveProperties(c);
        productValues = AbstractDAO.retrieveValues(c);


        for(String o : productFields) {
            if(o.equals("product_id"))
                continue;
            else wantedFields.add(o);
        }
        for(int i = 0; i < productValues.size(); i++) {
            if(i == 0)
                continue;
            else wantedValues.add(productValues.get(i));
        }
        insertQuery(wantedFields, wantedValues);
    }

    public ResultSet viewQueryProduct(Product c) {
        List<String> productFields = new ArrayList<String>();
        List<Object> productValues = new ArrayList<Object>();

        List<String> wantedFields = new ArrayList<String>();
        List<Object> wantedValues = new ArrayList<Object>();

        productFields = AbstractDAO.retrieveProperties(c);
        productValues = AbstractDAO.retrieveValues(c);
        ResultSet rs = null;

        for(int i = 0; i < productValues.size(); i++) {
            if(productFields.get(i).equals("product_id") || (productFields.get(i).equals("order_id") && (productValues.get(i) instanceof Integer) && ((Integer) productValues.get(i)).intValue() == 0)) {
                continue;
            }
            else {
                wantedFields.add(productFields.get(i));
                wantedValues.add(productValues.get(i));
            }
        }
        rs = viewByName(wantedFields, wantedValues);
        return rs;
    }
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<Product>();

        String queryString = "select * from Product";
        PreparedStatement statement = null;

        try {
            Connection connection = ConnectionWarehouse.getConnection();
            statement = connection.prepareStatement(queryString);
            ResultSet results = null;
            results = statement.executeQuery();
            products = createObjects(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void updateQueryProduct(Product p, Product updatedProduct) {
        List<String> productFields = new ArrayList<String>();
        List<Object> productValues = new ArrayList<Object>();
        List<Object> updatedProductValues = new ArrayList<Object>();

        List<String> wantedFields = new ArrayList<String>();
        List<Object> wantedValues = new ArrayList<Object>();
        List<Object> wantedUpdatedValues = new ArrayList<Object>();

        productFields = AbstractDAO.retrieveProperties(p);
        productValues = AbstractDAO.retrieveValues(p);
        updatedProductValues = AbstractDAO.retrieveValues(updatedProduct);

        for(int i = 0; i < productValues.size(); i++) {
            if(productFields.get(i).equals("product_id") || (productFields.get(i).equals("order_id") && (productValues.get(i) instanceof Integer) && ((Integer) productValues.get(i)).intValue() == 0)) {
                continue;
            }
            else {
                wantedFields.add(productFields.get(i));
                wantedValues.add(productValues.get(i));
            }
        }

        for(int i = 0; i < productValues.size(); i++) {
            if(productFields.get(i).equals("product_id") || (productFields.get(i).equals("order_id") && (updatedProductValues.get(i) instanceof Integer) && ((Integer) updatedProductValues.get(i)).intValue() == 0)) {
                continue;
            }
            else {
                wantedUpdatedValues.add(updatedProductValues.get(i));
            }
        }
        System.out.println(wantedValues);
        System.out.println(wantedUpdatedValues);
        updateQuery(wantedFields, wantedFields, wantedValues, wantedUpdatedValues);
    }

    public void deleteQueryProduct(Product p) {
        List<String> productFields = new ArrayList<String>();
        List<Object> productValues = new ArrayList<Object>();

        List<String> wantedFields = new ArrayList<String>();
        List<Object> wantedValues = new ArrayList<Object>();

        productFields = AbstractDAO.retrieveProperties(p);
        productValues = AbstractDAO.retrieveValues(p);

        for(int i = 0; i < productValues.size(); i++) {
            if(productFields.get(i).equals("product_id") || (productFields.get(i).equals("order_id") && (productValues.get(i) instanceof Integer) && ((Integer) productValues.get(i)).intValue() == 0)) {
                continue;
            }
            else {
                wantedFields.add(productFields.get(i));
                wantedValues.add(productValues.get(i));
            }
        }
        deleteQuery(wantedFields, wantedValues);
    }
}
