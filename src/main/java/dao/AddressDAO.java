package dao;

import entity.Address;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Vadim Dyachenko
 */
public interface AddressDAO {

    void add(Address adress) throws SQLException;

    List<Address> getAll() throws SQLException;

    Address getById(Long id) throws SQLException;

    void update(Address address) throws SQLException;

    void remove(Address address) throws SQLException;
}
