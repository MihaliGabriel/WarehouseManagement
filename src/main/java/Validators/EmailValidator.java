package Validators;
import Model.Customer;

import java.util.regex.*;

/**
 * Foloseste interfata Validator.
 * Este o clasa care implementeaza metoda validate() si valideaza email-ul unui client dat ca input din interfata.
 * Arunca o exceptie de tip IllegalArgumentException daca email-ul nu este valid.
 */
public class EmailValidator implements Validator<Customer>{
    private static final String EMAIL_PATTERN =  "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    @Override
    public void validate(Customer customer) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if(!pattern.matcher(customer.getCustomer_email()).matches()) {
            throw new IllegalArgumentException("Email is not a valid email!");
        }
    }
}
