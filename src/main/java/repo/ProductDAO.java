package repo;

import model.Product;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO implements DAO<Product> {

    /**
     * find and return all products
     *
     * @return all products
     * @throws SQLException ex
     */
    public ArrayList<Product> getAll() throws SQLException {

        String query = "SELECT * FROM product;";
        ArrayList<Product> products = new ArrayList<>();

        //// Execute SQL query and record response to string
        ResultSet result = getConnection()
                .createStatement()
                .executeQuery(query);

        // add to list
        while (result.next()) {
            int ID = result.getInt("id");
            String category = result.getString("category");
            String artist = result.getString("artist");
            String album = result.getString("album");
            String genre = result.getString("genre");
            String SKU = result.getString("sku");
            double price = result.getDouble("price");
            int quantity = result.getInt("quantity");

            products.add(new Product(ID, category, artist, album, genre, SKU, price, quantity));
        }

        return products;
    }

    /**
     * find product based on id
     *
     * @param id return product based on id
     * @return product
     * @throws SQLException ex
     */
    public Product get(int id) throws SQLException {

        String query = "SELECT * FROM product WHERE ID =" + id + ";";

        // find, init and return product from db
        ResultSet rs = getConnection().createStatement().executeQuery(query);
        while (rs.next()) {
//            int id = rs.getInt(1);
            String category = rs.getString(2);
            String artist = rs.getString(3);
            String album = rs.getString(4);
            String genre = rs.getString(5);
            String SKU = rs.getString(6);
            double price = rs.getDouble(7);
            int quantity = rs.getInt(8);

            return new Product(id, category, artist, album, genre, SKU, price, quantity);
        }

        return null;
    }


    /**
     * add new product
     *
     * @param item product
     * @return status
     * @throws SQLException ex
     */
    public int add(Product item) throws SQLException {

        //String insert = "INSERT INTO product" + " (ID, category, artist, album, genre, SKU, price, quantity)
        // VALUES " + " (?, ?, ?, ?, ?, ?, ?, ?);";
        String insert = "INSERT INTO product (category, artist, album, genre, sku, price, quantity) VALUES ("
                + "'" + item.getCategory() + "','" + item.getArtist() + "','" + item.getAlbum() + "','"
                + item.getGenre() + "','" + item.getSKU() + "'," + item.getPrice() + "," + item.getQuantity() + ");";

        try {
            PreparedStatement statement = getConnection()
                    .prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            // execute query - return generated id
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();

            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return -1;
        }
    }


    /**
     * update product
     *
     * @param item product
     * @return result
     * @throws SQLException ex
     */
    public boolean update(Product item) throws SQLException {
        String update = "UPDATE product " + "SET category = '" + item.getCategory() + "',"
                + "artist = '" + item.getArtist() + "'," + "album = '" + item.getAlbum() + "'," + "genre = '"
                + item.getGenre() + "',sku" + " = '" + item.getSKU() + "'," + "price = " + item.getPrice() + "," +
                " quantity = " + item.getQuantity() + " WHERE id = " + item.getId() + ";";

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

    /**
     * delete product based on id
     *
     * @param product_ID id
     * @return status
     * @throws SQLException ex
     */
    public boolean delete(int product_ID) throws SQLException {
        int result;
        String query = "DELETE FROM product WHERE id =" + product_ID + ";";

        result = getConnection()
                .createStatement()
                .executeUpdate(query);

        return result == 1;
    }
}
