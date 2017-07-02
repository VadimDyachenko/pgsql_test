package service;

import dao.EmplProjDAO;
import entity.EmplProj;
import repository.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadim Dyachenko
 */
public class EmplProjService extends Util implements EmplProjDAO {
    Connection connection = getConnection();

    @Override
    public void add(EmplProj emplProj) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO public.\"EMPL_PROJ\" (\"EMPLOYEE_ID\", \"PROJECT_ID\") VALUES(?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, emplProj.getEmployeeID());
            preparedStatement.setLong(2, emplProj.getProjectID());

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
    public List<EmplProj> getAll() throws SQLException {
        List<EmplProj> result = new ArrayList<>();
        String sql = "SELECT \"EMPLOYEE_ID\", \"PROJECT_ID\" FROM public.\"EMPL_PROJ\"";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                EmplProj emplProj = new EmplProj();
                emplProj.setEmployeeID(resultSet.getLong("EMPLOYEE_ID"));
                emplProj.setProjectID(resultSet.getLong("PROJECT_ID"));

                result.add(emplProj);
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
    public EmplProj getByEmployeeIdAndProjectId(Long employeeId, Long projectId) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT \"EMPLOYEE_ID\", \"PROJECT_ID\" FROM public.\"EMPL_PROJ\"" +
                " WHERE \"EMPLOYEE_ID\"=? AND \"PROJECT_ID\"=?";
        EmplProj emplProj = new EmplProj();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, employeeId);
            preparedStatement.setLong(2, projectId);

            ResultSet resultSet = preparedStatement.executeQuery();

            emplProj.setEmployeeID(resultSet.getLong("EMPLOYEE_ID"));
            emplProj.setProjectID(resultSet.getLong("PROJECT_ID"));

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

        return emplProj;
    }

    @Override
    public void update(EmplProj emplProj) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE public.\"EMPL_PROJ\" SET \"EMPLOYEE_ID\"=?, \"PROJECT_ID\"=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, emplProj.getEmployeeID());
            preparedStatement.setLong(2, emplProj.getProjectID());

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
    public void remove(EmplProj emplProj) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM public.\"EMPL_PROJ\" WHERE \"EMPLOYEE_ID\"=? AND \"PROJECT_ID\"=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, emplProj.getEmployeeID());
            preparedStatement.setLong(2, emplProj.getProjectID());

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
