import model.*;
import repo.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderDaoTest {

    private static OrderDAO orderDao;
    private static CustomerDAO customerDAO;
    private static ProductDAO productDAO;
    private static Order order;
    private static int orderID;
    private static ArrayList<OrderProduct> products;
    private static Customer customer;

    @Before
    public void setup() throws SQLException {

        if (products == null) {

            Address address = new Address();
            address.setHouseNo("1/C");
            address.setAddressLine1("London");
            address.setAddressLine2("UK");
            address.setCountry("UK");
            address.setPostcode("122");

            customer = new Customer();
            customerDAO = new CustomerDAO();
            customer.setCustomerSurname("Jhon");
            customer.setCustomerForename("Due");
            customer.setCustomerTelNo("00998877");
            customer.setCustomerAddress(address);
            int customerID = customerDAO.add(customer);
            customer = customerDAO.get(customerID);

            // products
            productDAO = new ProductDAO();
            Product product1 = new Product("C","C","C",
                    "C","C",100,100);
            int product1ID = productDAO.add(product1);
            product1.setId(product1ID);
            Product product2 = new Product("D","D","D",
                    "D","D",100,100);
            int product2ID = productDAO.add(product2);
            product2.setId(product2ID);

            products = new ArrayList<>();
            products.add(new OrderProduct(product1, 1));
            products.add(new OrderProduct(product2, 1));

            orderDao = new OrderDAO();
            order = new Order(customer, products);
        }

    }

    @Test
    public void test1Add() throws SQLException {
        int id = orderDao.add(order);
        orderID = id;

        assertNotEquals(-1, id);
    }

    @Test
    public void test2Update() throws SQLException {
        order.setCustomer(customer);
        order.setOrder_id(orderID);

        boolean status = orderDao.update(order);

        assertTrue(status);
        assertEquals(customer, orderDao.get(orderID).getCustomer());
    }

    @Test
    public void test3Get() throws SQLException {
        Order order = orderDao.get(orderID);
        assertNotEquals(null, order);
        assertNotEquals(0, orderDao.getAll().size());
    }

    @Test
    public void test3TotalPrice() throws SQLException {
        Order order = orderDao.get(orderID);
        int totalPrice = order.getTotalPrice();
        System.out.println(totalPrice);
    }

    @Test
    public void test4Delete() throws SQLException {
        customerDAO.delete(customer.getCustomerID());
        ArrayList<OrderProduct> products1 = order.getProducts();
        for (OrderProduct product:products1){
            productDAO.delete(product.getProduct().getId());
        }
        // delete cart
        boolean status = orderDao.delete(orderID);
        assertTrue(status);
        assertNull(orderDao.get(orderID));
    }
}
