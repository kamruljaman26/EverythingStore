import model.*;
import repo.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddressDaoTest {

    private static AddressDAO addressDAO;
    private static Address address;
    private static int addressId;

    // init
    @Before
    public void setup() {
        addressDAO = new AddressDAO();

        address = new Address();
        address.setHouseNo("house no");
        address.setAddressLine1("line 1");
        address.setAddressLine2("line 2");
        address.setCountry("country");
        address.setPostcode("post code");
    }

    @Test
    public void test1Add() throws SQLException {
        int id = addressDAO.add(address);
        addressId = id;
        assertNotEquals(-1, id);
    }

    @Test
    public void test2Update() throws SQLException {
        address.setCountry("New Country");
        address.setId(addressId);

        boolean status = addressDAO.update(address);

        assertTrue(status);
        assertEquals(addressDAO.get(address.getId()).getCountry(),
                address.getCountry());
    }

    @Test
    public void test3Get() throws SQLException {
        Address address = addressDAO.get(addressId);

        assertNotEquals(null, address);
        assertNotEquals(0, addressDAO.getAll().size());
    }

    @Test
    public void test4Delete() throws SQLException {
        boolean status = addressDAO.delete(addressId);
        assertTrue(status);
        assertNull(addressDAO.get(addressId));
    }
}
