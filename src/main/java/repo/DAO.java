package repo;

import util.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO Structure
 * @param <T>
 */
public interface DAO<T> {

    // db connection
    default Connection getConnection(){
        return DBConnection.getDBConnection();
    }

    // return all items
    ArrayList<T> getAll() throws SQLException;

    // return specific item
    T get(int id) throws SQLException;

    // delete item
    boolean delete(int id) throws SQLException;

    // update item
    boolean update(T item) throws SQLException;

    // add item
    int add(T item) throws SQLException;

}
