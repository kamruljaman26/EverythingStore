package com.everythingstore.repo;

import com.everythingstore.model.Address;
import java.sql.*;
import java.util.ArrayList;

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
    public int add(Address item) throws SQLException {
        // todo
        return -1;
    }
}
