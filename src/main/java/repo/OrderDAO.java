package repo;

import model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDAO implements DAO<Order> {

    @Override
    public ArrayList<Order> getAll() throws SQLException {
        String query = "SELECT * FROM order_;";

        ArrayList<Order> orders = new ArrayList<>();

        // find, init and return product from db
        ResultSet rs = getConnection().createStatement().executeQuery(query);
        while (rs.next()) {
            int order_id = rs.getInt(1);
            Customer customer = new CustomerDAO().get(rs.getInt(2));
            ArrayList<OrderProduct> orderProducts = new ArrayList<>();

            //// Execute SQL query and record response to string
            String queryProducts = "SELECT * FROM order_product WHERE order_id =" + order_id + ";";
            ResultSet result = getConnection()
                    .createStatement()
                    .executeQuery(queryProducts);

            // add to list
            while (result.next()) {
                int orderProductID = result.getInt("order_product_id");
                int product_id = result.getInt("product_id");
                Product product = new ProductDAO().get(product_id);
                int quantity = result.getInt("quantity");

                OrderProduct product1 = new OrderProduct(orderProductID, order_id, product, quantity);
                orderProducts.add(product1);
            }
            orders.add(new Order(order_id, customer, orderProducts));
        }

        return orders;
    }

    @Override
    public Order get(int id) throws SQLException {

        String query = "SELECT * FROM order_ WHERE order_id =" + id + ";";
        String queryProducts = "SELECT * FROM order_product WHERE order_id =" + id + ";";

        // find, init and return product from db
        ResultSet rs = getConnection().createStatement().executeQuery(query);
        while (rs.next()) {
            Customer customer = new CustomerDAO().get(rs.getInt(2));
            ArrayList<OrderProduct> orderProducts = new ArrayList<>();

            //// Execute SQL query and record response to string
            ResultSet result = getConnection()
                    .createStatement()
                    .executeQuery(queryProducts);

            // add to list
            while (result.next()) {
                int orderProductID = result.getInt("order_product_id");
                int product_id = result.getInt("product_id");
                Product product = new ProductDAO().get(product_id);
                int quantity = result.getInt("quantity");
                OrderProduct product1 = new OrderProduct(orderProductID, id, product, quantity);
//                System.out.println(product1);
                orderProducts.add(product1);
            }

            return new Order(id, customer, orderProducts);
        }

        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {

        // int product
        ArrayList<OrderProduct> products = get(id).getProducts();

        // delete order
        String query = "DELETE FROM order_ WHERE order_id =" + id + ";";
        int result = getConnection()
                .createStatement()
                .executeUpdate(query);

        // delete order products and update stocks
        products.forEach(product -> {
            try {
                String queryOrderProducts = "DELETE FROM order_product " +
                        "WHERE order_id =" + product.getOrderID() + ";";
                getConnection()
                        .createStatement()
                        .executeUpdate(queryOrderProducts);

                // update product stock
                ProductDAO productDAO = new ProductDAO();
                Product product1 = product.getProduct();
                if(product1 != null) {
                    Product updateStockProduct = productDAO.get(product.getProduct().getId());
                    updateStockProduct.setQuantity(updateStockProduct.getQuantity()
                            + product.getQuantity());
                    productDAO.update(updateStockProduct);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        return result == 1;
    }

    @Override
    public boolean update(Order item) throws SQLException {
        String update = "UPDATE order_ " + "SET customer_id = " + item.getCustomer().getCustomerID() +
                " WHERE order_id = " + item.getOrder_id() + ";";

        try {
            // execute order - only update customer id
            getConnection()
                    .createStatement()
                    .executeUpdate(update);

            // update all order products
            item.getProducts().forEach(orderProduct -> {

                // sql query for update products
                String updateOrderProduct = "UPDATE order_product SET order_id=" + orderProduct.getOrderID() + "," +
                        "product_id=" + orderProduct.getProduct().getId() + "," + "quantity=" + orderProduct.getQuantity() +
                        " WHERE order_product_id=" + orderProduct.getOrderProductID() + ";";
                try {
                    // update OrderProduct
                    getConnection()
                            .createStatement()
                            .executeUpdate(updateOrderProduct);

                    // update product stock
                    String queryProducts = "SELECT * FROM order_product WHERE order_product_id ="
                            + orderProduct.getOrderProductID() + ";";

                    //// Execute SQL query and record response to string
                    ResultSet result = getConnection()
                            .createStatement()
                            .executeQuery(queryProducts);

                    // if quantity is updated stock in product
                    while (result.next()) {
                        int quantity = result.getInt("quantity");

                        if (orderProduct.getQuantity() > quantity) {
                            ProductDAO productDAO = new ProductDAO();
                            Product updateProductItem = orderProduct.getProduct();
                            int newQuantity = orderProduct.getQuantity() - quantity;
                            if (newQuantity > 0)
                                updateProductItem.setQuantity(updateProductItem.getQuantity()
                                        - newQuantity);
                            productDAO.update(updateProductItem);
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public int add(Order item) throws SQLException {

        String query = "INSERT INTO order_ (customer_id) " +
                "VALUES (" + item.getCustomer().getCustomerID() + ");";
        try {

            // add order
            PreparedStatement statement = getConnection()
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            // execute query - return generated id
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int orderID = rs.getInt(1);

            // for each product create orderProducts
            ArrayList<OrderProduct> products = item.getProducts();
            products.forEach(e -> {
                String createOrderProductQuery = "INSERT INTO order_product(order_id, product_id, quantity) VALUES (" +
                        orderID + "," + e.getProduct().getId() + "," + e.getQuantity() + ");";
                try {
                    PreparedStatement stat = getConnection().prepareStatement(createOrderProductQuery);
                    stat.executeUpdate();

                    ResultSet rs2 = statement.getGeneratedKeys();
                    rs2.next();

//                    int orderID2 = rs2.getInt(1);
//                    System.out.println("product_order_id:" + orderID2);

                    // update product stock
                    ProductDAO productDAO = new ProductDAO();
                    Product product = e.getProduct();
                    product.setQuantity(product.getQuantity() - e.getQuantity());
                    productDAO.update(product);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
            });

            return orderID;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

}
