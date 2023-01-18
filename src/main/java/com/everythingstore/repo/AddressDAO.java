package com.everythingstore.repo;

import com.everythingstore.model.Address;
import com.everythingstore.model.Product;
import com.everythingstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO implements DAO<Address>{

    @Override
    public ArrayList<Address> getAll() throws SQLException {
        //todo
        return null;
    }

    @Override
    public Address get(int id) throws SQLException {
        //todo
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        //todo
        return false;
    }

    @Override
    public boolean update(Address item) throws SQLException {
        //todo
        return false;
    }

    @Override
    public boolean add(Address item) throws SQLException {
        // todo
        return false;
    }
}
