package DataAccess;

import Model.*;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.util.ArrayList;
import java.util.List;

/**
 * Ca si CustomerDAO,
 * extinde clasa AbstractDAO, iar in T se pune OrderBill.
 */
public class OrderDAO extends AbstractDAO<OrderBill>{
    public void createOrderQuery(int customer_id, int product_id, int price, OrderBill order) {
        List<String> orderFields = new ArrayList<String>();
        List<Object> orderValues = new ArrayList<Object>();

        List<String> wantedFields = new ArrayList<String>();
        List<Object> wantedValues = new ArrayList<Object>();

        orderFields = AbstractDAO.retrieveProperties(order);
        orderValues = AbstractDAO.retrieveValues(order);

        for(String o : orderFields) {
            if(o.equals("orderID"))
                continue;
            else wantedFields.add(o);
        }
        wantedValues.add(customer_id);
        wantedValues.add(product_id);
        wantedValues.add(price);

        insertQuery(wantedFields, wantedValues);
    }
}
