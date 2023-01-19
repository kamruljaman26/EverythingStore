package com.everythingstore.repo;

import com.everythingstore.model.Cart;
import com.everythingstore.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CartDAO implements DAO<Cart> {

    @Override
    public ArrayList<Cart> getAll() throws SQLException {
        String query = "SELECT * FROM cart;";
        ArrayList<Cart> carts = new ArrayList<>();

        //// Execute SQL query and record response to string
        ResultSet result = getConnection()
                .createStatement()
                .executeQuery(query);

        // add to list
        while (result.next()) {
            int id = result.getInt("cart_id");
            Product product = new ProductDAO().get(result.getInt("product_id"));
            int quantity = result.getInt("quantity");

            carts.add(new Cart(id, product, quantity));
        }

        return carts;
    }

    @Override
    public Cart get(int id) throws SQLException {
        String query = "SELECT * FROM cart WHERE cart_id =" + id + ";";

        // find, init and return product from db
        ResultSet rs = getConnection().createStatement().executeQuery(query);
        while (rs.next()) {
            Product product = new ProductDAO().get(rs.getInt(2));
            int quantity = rs.getInt(3);
            return new Cart(id, product, quantity);
        }

        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        int result;
        String query = "DELETE FROM cart WHERE cart_id =" + id + ";";

        result = getConnection()
                .createStatement()
                .executeUpdate(query);

        return result == 1;
    }

    @Override
    public boolean update(Cart item) throws SQLException {
        String update = "UPDATE cart " + "SET product_id = " + item.getProduct().getId() + "," +
                "quantity = " + item.getQuantity() + " WHERE cart_id = " + item.getCartID() + ";";

        try {
            // execute query
            getConnection()
                    .createStatement()
                    .executeUpdate(update);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public int add(Cart item) throws SQLException {

        String query = "INSERT INTO cart (product_id, quantity) VALUES (" + item.getProduct().getId()
                + "," + item.getQuantity() + ");";

        try {
            PreparedStatement statement = getConnection()
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            // execute query - return generated id
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();

            return rs.getInt(1);
        } catch (SQLException e) {

            /*
             * if product is already added in card then implement following algorithm
             * Step 1: find cart with the product
             * Step 2: set cart quantity
             * Step 3: update the cart with new quantity
             */
            if (e.getMessage().contains("cart.product_id")) {
                try {
                    // find cart using product id
                    String findQuery = "SELECT * FROM cart WHERE product_id =" + item.getProduct().getId() + ";";
                    ResultSet rs = getConnection().createStatement().executeQuery(findQuery);
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        Cart cart = get(id);

                        // set cart quantity
                        cart.setQuantity(cart.getQuantity() + item.getQuantity());

                        // update cart
                        update(cart);

                        return id;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        return -1;
    }
}
