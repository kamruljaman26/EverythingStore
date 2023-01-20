import model.*;
import repo.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CartDaoTest {

    private static CartDAO cartDao;
    private static Cart cart;
    private static int cartID;
    private static int productID;
    private static Product product;


    // init
    @Before
    public void setup() throws SQLException {
        cartDao = new CartDAO();

        if (product == null || cart == null) {
            product = new Product();
            product.setAlbum("Album");
            product.setCategory("Category");
            product.setArtist("Artist");
            product.setGenre("Genre");
            product.setSKU("SKU-CartTest-13");
            product.setPrice(200);
            product.setQuantity(100);

            int id = new ProductDAO().add(product);
            System.out.println(id);
            productID = id;
            product.setId(productID);

            cart = new Cart();
            cart.setQuantity(1);
            cart.setProduct(product);
        }
    }

    @Test
    public void test1Add() throws SQLException {
        int id = cartDao.add(cart);
        cartID = id;

        assertNotEquals(-1, id);
    }

    @Test
    public void test2Update() throws SQLException {
        cart.setQuantity(5);
        cart.setCartID(cartID);

        boolean status = cartDao.update(cart);

        assertTrue(status);
        assertEquals(5, cartDao.get(cartID).getQuantity());
    }

    @Test
    public void test3GetProducts() throws SQLException {
        Cart cart = cartDao.get(cartID);

        assertNotEquals(null, cart);
        assertNotEquals(0, cartDao.getAll().size());
    }

    @Test
    public void test4DeleteProducts() throws SQLException {
        // delete cart
        boolean status = cartDao.delete(cartID);
        assertTrue(status);
        assertNull(cartDao.get(cartID));

        // delete product
        System.out.println(productID);
        new ProductDAO().delete(productID);
    }
}
