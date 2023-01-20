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
    private static Order order;
    private static int orderID;
    private static ArrayList<OrderProduct> products;
    private static Customer customer;

    @Before
    public void setup() throws SQLException {

        if (products == null) {
            customer = new CustomerDAO().get(1);
            ProductDAO productDAO = new ProductDAO();

            products = new ArrayList<>();
            products.add(new OrderProduct(customer.getCustomerID(), productDAO.get(1), 1));
            products.add(new OrderProduct(customer.getCustomerID(), productDAO.get(2), 1));
            products.add(new OrderProduct(customer.getCustomerID(), productDAO.get(3), 1));
            products.add(new OrderProduct(customer.getCustomerID(), productDAO.get(4), 1));
        }

        orderDao = new OrderDAO();
        order = new Order(customer, products);
    }

    @Test
    public void test1Add() throws SQLException {
        int id = orderDao.add(order);
        orderID = id;

        assertNotEquals(-1, id);
    }

    @Test
    public void test2Update() throws SQLException {
        order.setCustomer(new CustomerDAO().get(2));
        order.setOrder_id(orderID);

        boolean status = orderDao.update(order);

        assertTrue(status);
        assertEquals(new CustomerDAO().get(2), orderDao.get(orderID).getCustomer());
    }

    @Test
    public void test3GetProducts() throws SQLException {
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
    public void test4DeleteProducts() throws SQLException {
        // delete cart
        boolean status = orderDao.delete(orderID);
        assertTrue(status);
        assertNull(orderDao.get(orderID));
    }
}
