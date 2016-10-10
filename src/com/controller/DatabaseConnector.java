package com.controller;

import com.beans.User;

import java.sql.*;

/**
 * Created by Lena on 31.05.2016.
 */
public class DatabaseConnector {

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/kbrs_lab_1?user=root&password=1111&useSSL=true");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkUser(User user) {
        boolean isValid = false;
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT id, name, password FROM users WHERE name LIKE ?;");
            statement.setString(1, user.getName());
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                if (user.getPassword().equals(set.getString("password"))) {
                    user.setId(set.getInt("id"));
                    isValid = true;
                } else isValid = false;
            }
            else isValid = false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if(connection!=null)
                    connection.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }

        return isValid;
    }

   public User getUserById(int id) {
        Connection connection = null;
        User result = new User();
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT id, name, password FROM users WHERE id = ?;");
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                result.setId(id);
                result.setName(set.getString("name"));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if(connection!=null)
                    connection.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return result;
  }


  /*  public List<Professor> getProfessorsList() {
        List<Professor> result = new ArrayList<>();
        String sql = "SELECT id, name, surname, secondName, user_id FROM professor;";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            result = parseResultSet(set);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<Professor>parseResultSet(ResultSet set) {
        List<Professor> list = new ArrayList<>();
        try {
            while (set.next()) {
                Professor professor = new Professor();
                professor.setName(set.getString("name"));
                professor.setSecondName(set.getString("secondName"));
                professor.setSurname(set.getString("surname"));
                professor.setId(set.getInt("id"));
                User user = getUserById(set.getInt("user_id"));
                professor.setUser(user);
                list.add(professor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Professor getProfessor(int id) {
        List<Professor> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT id, name, surname, secondName, user_id FROM professor WHERE id = ?;");
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            result = parseResultSet(set);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.get(0);
    }

    public void addProfessor(Professor professor) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO professor" +
                    " (name, surname, secondName)" +
                    " VALUES(?,?,?);");
            statement.setString(1, professor.getName());
            statement.setString(2, professor.getSurname());
            statement.setString(3, professor.getSecondName());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateProfessor(Professor professor) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE professor" +
                    " SET name=?," +
                    " surname=?," +
                    " secondName=?" +
                    " WHERE id=?;");
            statement.setString(1, professor.getName());
            statement.setString(2, professor.getSurname());
            statement.setString(3, professor.getSecondName());
            statement.setInt(4, professor.getId());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteProfessor(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM professor WHERE id =?;");
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }*/

}
