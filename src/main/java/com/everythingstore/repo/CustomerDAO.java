package com.everythingstore.repo;

import com.everythingstore.model.Address;
import com.everythingstore.model.Customer;
import com.everythingstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAO implements DAO<Customer> {

    @Override
    public Connection getConnection() {
        return DBConnection.getDBConnection();
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException {
        ResultSet result = null;
        String query = "SELECT * FROM " + "customer";
        ArrayList<Customer> customers = new ArrayList<>();

        // Execute SQL query and record response to string
        result = getConnection().createStatement().executeQuery(query);

        while (result.next()) {
            int customerID = result.getInt(1);
            String customerForename = result.getString(2);
            String customerSurname = result.getString(3);
            Address customerAddress = new AddressDAO().get(Integer.parseInt(result.getString(4)));
            String customerTelNo = result.getString(5);

            customers.add(new Customer(customerID, customerForename, customerSurname, customerAddress, customerTelNo));
        }

        return customers;
    }

    @Override
    public Customer get(int id) throws SQLException {

        String query = "SELECT * FROM customer WHERE customer_id = ?";

        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {

            String forename = rs.getString("customer_fore_name");
            String surname = rs.getString("customer_sur_name");
            Address address = new AddressDAO().get(rs.getInt("customer_address"));
            String telno = rs.getString("customer_tel_no");

            return new Customer(id, forename, surname, address, telno);
        }

        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {

        // delete address
        new AddressDAO().delete(get(id).getCustomerAddress().getId());

        int result;
        String query = "DELETE FROM customer WHERE customer_id =" + id + ";";
        result = getConnection()
                .createStatement()
                .executeUpdate(query);

        return result == 1;
    }

    @Override
    public boolean update(Customer item) throws SQLException {

        System.out.println(item.getCustomerAddress().getId());

        // update customer
        String update = "UPDATE customer SET customer_fore_name = '"
                + item.getCustomerForename() + "'," + "customer_sur_name = '" + item.getCustomerSurname() + "',"
                + "customer_address = " + item.getCustomerAddress().getId() + ", customer_tel_no = '" + item.getCustomerTelNo()
                + "' WHERE customer_id = " + item.getCustomerID() + ";";

        new AddressDAO().update(item.getCustomerAddress()); // update address

        try {
            // execute query
            getConnection().createStatement().executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int add(Customer item) throws SQLException {

        // create address for customer
        AddressDAO addressDAO = new AddressDAO();
        int addressID = addressDAO.add(item.getCustomerAddress());
        item.getCustomerAddress().setId(addressID);

        String insert = "INSERT INTO customer (customer_fore_name, customer_sur_name, customer_address, customer_tel_no)" +
                "VALUES (" + "'" + item.getCustomerForename() + "','" + item.getCustomerSurname() + "','" + addressID +
                "','" + item.getCustomerTelNo() + "');";

        try {
            PreparedStatement statement = getConnection()
                    .prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            // execute query - return generated id
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();

            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}




