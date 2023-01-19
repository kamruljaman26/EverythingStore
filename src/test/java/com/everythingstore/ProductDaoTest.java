package com.everythingstore;

import com.everythingstore.model.Product;
import com.everythingstore.repo.ProductDAO;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import java.sql.SQLException;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest {

    private static ProductDAO productDAO;
    private static Product product;
    private static int productID;

    // init
    @Before
    public void setup() {
        productDAO = new ProductDAO();

        product = new Product();
        product.setAlbum("Album");
        product.setCategory("Category");
        product.setArtist("Artist");
        product.setGenre("Genre");
        product.setSKU("SKU");
        product.setPrice(200);
        product.setQuantity(100);
    }

    @Test
    public void test1Add() throws SQLException {
        int id = productDAO.add(product);
        productID = id;
        assertNotEquals(-1, id);
    }

    @Test
    public void test2Update() throws SQLException {
        product.setCategory("New Category");
        product.setId(productID);

        boolean status = productDAO.update(product);

        assertTrue(status);
        assertEquals(productDAO.get(product.getId()).getCategory(),
                product.getCategory());
    }

    @Test
    public void test3GetProducts() throws SQLException {
        Product product = productDAO.get(productID);

        assertNotEquals(null, product);
        assertNotEquals(0, productDAO.getAll().size());
    }

    @Test
    public void test4DeleteProducts() throws SQLException {
        boolean status = productDAO.delete(productID);
        assertTrue(status);
        assertNull(productDAO.get(productID));
    }
}
