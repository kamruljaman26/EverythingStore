import model.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import repo.*;

import java.sql.SQLException;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerDaoTest {

    private static CustomerDAO customerDAO;
    private static Customer customer;
    private static Address address;
    private static int customerID;
    private static int addressID;

    // init
    @Before
    public void setup() {
        customerDAO = new CustomerDAO();

        // init address
        address = new Address();
        address.setHouseNo("house no");
        address.setAddressLine1("line 1");
        address.setAddressLine2("line 2");
        address.setCountry("country");
        address.setPostcode("post code");

        // init customer
        customer = new Customer();
        customer.setCustomerForename("Fore Name");
        customer.setCustomerSurname("Sur Name");
        customer.setCustomerAddress(address);
        customer.setCustomerTelNo("Tel No");
    }

    @Test
    public void test1Add() throws SQLException {
        customerID = customerDAO.add(customer);

        // update address id
        addressID = customerDAO.get(customerID).getCustomerAddress().getId();
        System.out.println("address: " + addressID);

        assertNotEquals(-1, customerID);
    }

    @Test
    public void test2Update() throws SQLException {

        // update a field
        customer.setCustomerSurname("New Country");

        // update address & ids
        address.setAddressLine1("new line 1");
        customer.getCustomerAddress().setId(addressID);
        customer.setCustomerID(customerID);

        boolean status = customerDAO.update(customer);

        assertTrue(status);
        assertEquals(customerDAO.get(customerID).getCustomerForename(), customer.getCustomerForename());
        assertEquals(customerDAO.get(customerID).getCustomerAddress().getAddressLine1(), address.getAddressLine1());

    }

    @Test
    public void test3GetProducts() throws SQLException {
        Customer customer = customerDAO.get(customerID);

        assertNotEquals(null, customer);
        assertNotEquals(0, customerDAO.getAll().size());
    }

    @Test
    public void test4DeleteProducts() throws SQLException {
        boolean status = customerDAO.delete(customerID);
        assertTrue(status);
        assertNull(customerDAO.get(customerID));
        assertNull(new AddressDAO().get(addressID));
    }
}
