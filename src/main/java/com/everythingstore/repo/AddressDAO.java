package com.everythingstore.repo;

import com.everythingstore.model.Address;
import com.everythingstore.model.Product;

import java.sql.*;
import java.util.ArrayList;

public class AddressDAO implements DAO<Address> {

    @Override
    public ArrayList<Address> getAll() throws SQLException {
        String query = "SELECT * FROM address;";
        ArrayList<Address> addresses = new ArrayList<>();

        //// Execute SQL query and record response to string
        ResultSet result = getConnection()
                .createStatement()
                .executeQuery(query);

        // add to list
        while (result.next()) {

            int id = result.getInt("id");
            String houseNo = result.getString("house_no");
            String addressLine1 = result.getString("address_line_1");
            String addressLine2 = result.getString("address_line_1");
            String country = result.getString("country");
            String postCode = result.getString("post_code");

            addresses.add(new Address(id, houseNo, addressLine1, addressLine2, country, postCode));
        }

        return addresses;
    }

    @Override
    public Address get(int id) throws SQLException {
        String query = "SELECT * FROM address WHERE ID =" + id + ";";

        // find, init and return product from db
        ResultSet rs = getConnection().createStatement().executeQuery(query);
        while (rs.next()) {
            String houseNo = rs.getString(2);
            String addressLine1 = rs.getString(3);
            String addressLine2 = rs.getString(4);
            String country = rs.getString(5);
            String postCode = rs.getString(6);

            return new Address(id, houseNo, addressLine1, addressLine2, country, postCode);
        }

        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        int result;
        String query = "DELETE FROM address WHERE id =" + id + ";";

        result = getConnection()
                .createStatement()
                .executeUpdate(query);

        return result == 1;
    }

    @Override
    public boolean update(Address item) throws SQLException {
        String update = "UPDATE address " + "SET house_no = '" + item.getHouseNo() + "',"
                + "address_line_1 = '" + item.getAddressLine1() + "'," + "address_line_2 = '"
                + item.getAddressLine2() + "'," + "country = '" + item.getCountry() + "',post_code"
                + " = '" + item.getPostcode() + "' WHERE id = " + item.getId() + ";";

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
    public int add(Address item) throws SQLException {

        String query = "INSERT INTO address (house_no, address_line_1, address_line_2, country," +
                " post_code) VALUES (" + "'" + item.getHouseNo() + "','" + item.getAddressLine1() + "','" +
                item.getAddressLine2() + "','" + item.getCountry() + "','" + item.getPostcode() + "');";

        try {
            PreparedStatement statement = getConnection()
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            // execute query - return generated id
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();

            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
}
