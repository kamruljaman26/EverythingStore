package com.everythingstore.repo;

import com.everythingstore.model.Customer;
import com.everythingstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements DAO<Customer>{

    @Override
    public Connection getConnection() {
        return DBConnection.getDBConnection();
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException {
        return null;
    }

    @Override
    public Customer get(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Customer item) throws SQLException {
        return false;
    }

    @Override
    public boolean add(Customer item) throws SQLException {
        return false;
    }
}




