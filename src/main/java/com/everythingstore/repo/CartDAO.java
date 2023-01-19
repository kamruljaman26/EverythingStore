package com.everythingstore.repo;

import com.everythingstore.model.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class CartDAO implements DAO<Order> {

    @Override
    public ArrayList<Order> getAll() throws SQLException {
        return null;
    }

    @Override
    public Order get(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Order item) throws SQLException {
        return false;
    }

    @Override
    public int add(Order item) throws SQLException {
        return -1;
    }
}
