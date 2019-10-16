package com.geekbrains.dao;

import com.geekbrains.entities.Disk;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;

public class SimpleDiskDao implements DiskDao {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            // logger.error("Problem loadng DB dDiver!", ex);
            ex.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/geek_db?useSSL=false",
                "geek", "geek");
    }

    private void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            // logger.error("Problem closing connection to the database!",ex);
        }
    }

    @Override
    public void insert(Disk disk) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO disks (title) VALUES (?);",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, disk.getTitle());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                disk.setId(generatedKeys.getLong(1));
            }
            statement.close();
        } catch (SQLException ex) {
            // logger.error("Problem executing INSERT", ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public String findTitleById(Long id) {
        throw new NotImplementedException();
    }
}
