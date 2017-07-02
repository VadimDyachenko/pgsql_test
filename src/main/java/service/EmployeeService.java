package service;

import dao.EmployeeDAO;
import entity.Employee;
import repository.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadim Dyachenko
 */
public class EmployeeService extends Util implements EmployeeDAO {
    Connection connection = getConnection();

    @Override
    public void add(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO public.\"EMPLOYEE\" (\"ID\", \"FIRST_NAME\", \"LAST_NAME\", \"BIRTHDAY\", \"ADDRESS_ID\")" +
                " VALUES(?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, employee.getId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setDate(4, employee.getBirthday());
            preparedStatement.setLong(5, employee.getAdressID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        List<Employee> result = new ArrayList<>();
        String sql = "SELECT \"ID\", \"FIRST_NAME\", \"LAST_NAME\", \"BIRTHDAY\", \"ADDRESS_ID\" FROM public.\"EMPLOYEE\"";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("ID"));
                employee.setFirstName(resultSet.getString("FIRST_NAME"));
                employee.setLastName(resultSet.getString("LAST_NAME"));
                employee.setBirthday(resultSet.getDate("BIRTHDAY"));
                employee.setAdressID(resultSet.getLong("ADDRESS_ID"));

                result.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return result;
    }

    @Override
    public Employee getById(Long id) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT \"ID\", \"FIRST_NAME\", \"LAST_NAME\", \"BIRTHDAY\", \"ADDRESS_ID\" FROM public.\"EMPLOYEE\" " +
                "WHERE \"ID\"=?";
        Employee employee = new Employee();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            employee.setId(resultSet.getLong("ID"));
            employee.setFirstName(resultSet.getString("FIRST_NAME"));
            employee.setLastName(resultSet.getString("LAST_NAME"));
            employee.setBirthday(resultSet.getDate("BIRTHDAY"));
            employee.setAdressID(resultSet.getLong("ADDRESS_ID"));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return employee;
    }

    @Override
    public void update(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE public.\"EMPLOYEE\" SET \"FIRST_NAME\"=?, \"LAST_NAME\"=?, \"BIRTHDAY\"=?, \"ADDRESS_ID\"=?" +
                " WHERE \"ID\"=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setDate(3, employee.getBirthday());
            preparedStatement.setLong(4, employee.getAdressID());
            preparedStatement.setLong(5, employee.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void remove(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM public.\"EMPLOYEE\" WHERE \"ID\"=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, employee.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
