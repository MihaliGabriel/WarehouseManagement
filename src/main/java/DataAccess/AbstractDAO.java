package DataAccess;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import Connection.ConnectionWarehouse;
import Model.Customer;

/**
 * Are 2 constante, LOGGER si type, si in nume parametrul T.
 * Aceasta clasa contine metode de creare string-uri pentru query-uri generice
 * , de creare a query-urilor generice, de returnare a proprietatilor unei clase si a valorilor campurilor unei clase, prin reflection.
 * @param <T>
 */
public class AbstractDAO<T> {
    protected final static Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")

    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String createInsertQuery(List<String> fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append("(");
        for(Object s : fields) {
            sb.append(s + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(") ");
        sb.append("values");
        sb.append("(");
        for(int i = 0; i < fields.size(); i++) {
            sb.append("?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }

    public String createSelectQuery(List<String> fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ");
        for(int i = 0; i < fields.size(); i++) {
            sb.append(fields.get(i) + "=? and ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    public String createUpdateQuery(List<String> fields, List<String> fieldstoUpdate) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        for(int i = 0; i < fieldstoUpdate.size(); i++) {
            sb.append(fieldstoUpdate.get(i) + "=?, ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE " );
        for(int i = 0; i < fields.size(); i++) {
            sb.append(fields.get(i) + "=? " + "and ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();

    }

    public String createDeleteQuery(List<String> fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ");
        for(int i = 0; i < fields.size(); i++) {
            sb.append(fields.get(i) + "=? and ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

   public void deleteQuery(List<String> fields, List<Object> values) {
       Connection connection = null;
       PreparedStatement statement = null;
       int i = 0;
       String query = createDeleteQuery(fields);
       try {
           connection = ConnectionWarehouse.getConnection();
           statement = connection.prepareStatement(query);
           for(i = 1; i <= values.size(); i++) {
               statement.setString(i, String.valueOf(values.get(i-1)));
           }
           statement.execute();
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   public void updateQuery(List<String> fields, List<String> fieldstoUpdate, List<Object> values, List<Object> updatedValues) {
        Connection connection = null;
        PreparedStatement statement = null;
        int i = 0;
       String query = createUpdateQuery(fields, fieldstoUpdate);
       System.out.println(query);
        try {
            connection = ConnectionWarehouse.getConnection();
            statement = connection.prepareStatement(query);
            for(i = 1; i <= updatedValues.size(); i++) {
                statement.setString(i, String.valueOf(updatedValues.get(i-1)));
            }
            i--;
            for(int j = 1; j <= values.size(); j++) {
                if(j + i <= values.size() + updatedValues.size())
                    statement.setString(j + i, String.valueOf(values.get(j - 1)));
            }
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertQuery(List<String> fields, List<Object> values) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(fields);
        System.out.println(query);
        int id = 0;
            try {
                connection = ConnectionWarehouse.getConnection();
                statement = connection.prepareStatement(query);
                for(int i = 1; i <= values.size(); i++) {
                    if (values.get(i-1) instanceof Integer)
                        statement.setInt(i, ((Integer)values.get(i-1)).intValue());
                    if (values.get(i-1) instanceof String) {
                        statement.setString(i, String.valueOf(values.get(i-1)));
                        System.out.println(values.get(i-1));
                    }
                }
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public ResultSet viewByName(List<String> nameFields, List<Object> nameValues) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createSelectQuery(nameFields);
        System.out.println(query);
        ResultSet rs = null;
        int id = 0;
        try {
            connection = ConnectionWarehouse.getConnection();
            statement = connection.prepareStatement(query);
            for(int i = 1; i <= nameFields.size(); i++) {
                statement.setString(i, String.valueOf(nameValues.get(i-1)));
            }
            rs = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();

        try {
            while(resultSet.next()) {
                T instance = type.newInstance();
                for(Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    if(value != null) {
                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                        Method method = propertyDescriptor.getWriteMethod();
                        method.invoke(instance, value);
                    }
                }
                list.add(instance);
            }
        }catch(InstantiationException | IntrospectionException | IllegalAccessException | SQLException | InvocationTargetException e) {
            e.printStackTrace();
        }
       return list;
    }

    public static List<String> retrieveProperties(Object object) {
        List<String> objectFields = new ArrayList<String>();
        for(Field field : object.getClass().getDeclaredFields()) {
                objectFields.add(field.getName());
        }
        return objectFields;
    }

    public static List<Object> retrieveValues(Object object) {
        List<Object> objectValues = new ArrayList<Object>();
        for(Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
                try {
                    value = field.get(object);
                    objectValues.add(value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
        return objectValues;
    }
}

