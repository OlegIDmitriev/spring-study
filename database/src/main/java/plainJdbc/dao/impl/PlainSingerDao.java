package plainJdbc.dao.impl;

import entity.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import plainJdbc.dao.SingerDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlainSingerDao implements SingerDAO {
    private static Logger logger = LoggerFactory.getLogger(PlainSingerDao.class);

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            logger.error("Problem with loading DB driver!", ex);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "Idaho_0711");
    }

    private void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
        } catch (SQLException ex) {
            logger.error("Problem closing connection to the database!", ex);
        }
    }

    @Override
    public List<Singer> findAll() {
        List<Singer> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from singer");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Singer singer = new Singer();
                singer.setId(resultSet.getLong("id"));
                singer.setFirstName(resultSet.getString("first_name"));
                singer.setLastName(resultSet.getString("last_name"));
                singer.setBirthDate(resultSet.getDate("birth_date"));
                result.add(singer);
            }
            statement.close();
        } catch (SQLException ex) {
            logger.error("Problem when executing SELECT!", ex);
        } finally {
            closeConnection(connection);
        }

        return result;
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        List<Singer> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from singer where first_name = ?");
            statement.setString(1, firstName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Singer singer = new Singer();
                singer.setId(resultSet.getLong("id"));
                singer.setFirstName(resultSet.getString("first_name"));
                singer.setLastName(resultSet.getString("last_name"));
                singer.setBirthDate(resultSet.getDate("birth_date"));
                result.add(singer);
            }
            statement.close();
        } catch (SQLException ex) {
            logger.error("Problem when executing SELECT!", ex);
        } finally {
            closeConnection(connection);
        }

        return result;
    }

    @Override
    public String findLastNameById(Long id) {
        String lastName = null;
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("select last_name from singer where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                lastName = resultSet.getString("last_name");
            }
            statement.close();
        } catch (SQLException ex) {
            logger.error("Problem when executing SELECT last Name!", ex);
        } finally {
            closeConnection(connection);
        }

        return lastName;
    }

    @Override
    public String findFirstNameById(Long id) {
        String firstName = null;
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("select first_name from singer where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                firstName = resultSet.getString("last_name");
            }
            statement.close();
        } catch (SQLException ex) {
            logger.error("Problem when executing SELECT last Name!", ex);
        } finally {
            closeConnection(connection);
        }

        return firstName;
    }

    @Override
    public void insert(Singer singer) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into Singer(first_name, last_name, birth_date) " +
                    "VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, singer.getFirstName());
            statement.setString(2, singer.getLastName());
            statement.setDate(3, singer.getBirthDate());
            statement.execute();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                singer.setId((generatedKeys.getLong(1)));
            }
            statement.close();

        } catch (SQLException ex) {
            logger.error("Problem with executing INSERT", ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Singer singer) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("update Singer set first_name = ?, last_name = ?, birth_date = ? " +
                    "where id = ?");
            statement.setString(1, singer.getFirstName());
            statement.setString(2, singer.getLastName());
            statement.setDate(3, singer.getBirthDate());
            statement.setLong(4, singer.getId());
            statement.executeUpdate();

            statement.close();

        } catch (SQLException ex) {
            logger.error("Problem with executing UPDATE", ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(Long singerId) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("delete from Singer where id=?");
            statement.setLong(1, singerId);
            statement.execute();
            statement.close();

        } catch (SQLException ex) {
            logger.error("Problem with executing DELETE!", ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void insertWithAlbum(Singer singer) {

    }

    @Override
    public String findNameById(Long id) {
        return null;
    }

    @Override
    public List<Singer> findAllWithAlbums() {
        return null;
    }
}
