package com.controller;

import com.beans.User;

import java.sql.*;

public class DatabaseConnector {

    public static final String ADD_ARTICLE = "INSERT INTO ARTICLES (AUTHOR_ID, NAME, TEXT) VALUES(?,?,?)";
    public static final String GET_ALL = "SELECT ID, AUTHOR_ID, NAME, TEXT FROM ARTICLES";
    public static final String GET_ARTICLE = "SELECT ID, AUTHOR_ID, NAME, TEXT FROM ARTICLES WHERE ID=?";
    public static final String DELETE_ARTICLE = "DELETE FROM ARTICLES WHERE ID=?";
    public static final String CHANGE_ARTICLE = "UPDATE ARTICLES SET NAME=?, TEXT=? WHERE ID=?";

    private Connection getConnection() throws SQLException {
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

   /* public List<Article> getArticleList() {
        List<Article> result = new ArrayList<>();
        String sql = "SELECT * FROM articles;";
        // String sql1 = "SELECT * FROM permissions WHERE article_id = ?;";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            // statement = connection.prepareStatement(sql1);
            // statement.setString(1, .getName());
            //  ResultSet set1 = statement.executeQuery();
            result = parseArticleResultSet(set, connection);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return result;
    }
*/
   /* private List<Article> parseArticleResultSet(ResultSet set, Connection connection) {
        List<Article> list = new ArrayList<>();
        String sql1 = "SELECT * FROM permissions WHERE article_id = ?;";
        try {
            while (set.next()) {
                Article article = new Article();
                article.setName(set.getString("name"));
                article.setText(set.getString("text"));
                article.setId(set.getInt("id"));
                PreparedStatement statement = connection.prepareStatement(sql1);
                statement.setInt(1, article.getId());
                ResultSet set1 = statement.executeQuery();
                if (set1.next()) {
                    Map<Integer, ArrayList<PermissionType>> permissions = new HashMap<>();
                    do {
                        //   User user = getUserById(set1.getInt("user_id"));
                        PermissionType permissionType = PermissionType.getById(set1.getInt("permission_type_id"));
                        if (permissions.containsKey(set1.getInt("user_id"))) {
                            permissions.get(set1.getInt("user_id")).add(permissionType);
                        } else {
                            permissions.put(set1.getInt("user_id"), new ArrayList<PermissionType>() {{
                                add(permissionType);
                            }});
                        }
                    } while (set1.next());
                    article.setPermissions(permissions);
                }
                list.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
*/
  /*

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
