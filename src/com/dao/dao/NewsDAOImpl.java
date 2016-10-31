package com.dao.dao;


import com.beans.Article;
import com.beans.PermissionType;
import com.beans.User;
import com.dao.connector.DBConnector;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class NewsDAOImpl implements NewsDAO<Article> {

    public static final String ADD_ARTICLE = "INSERT INTO ARTICLES (AUTHOR_ID, NAME, TEXT) VALUES(?,?,?)";
    public static final String GET_ALL = "SELECT ID, AUTHOR_ID, NAME, TEXT FROM ARTICLES";
    public static final String GET_ARTICLE = "SELECT ID, AUTHOR_ID, NAME, TEXT FROM ARTICLES WHERE ID=?";
    public static final String GET_ARTICLES_TO_VIEW = "SELECT DISTINCT a.ID, a.AUTHOR_ID, a.NAME, a.TEXT  " +
            "FROM ARTICLES a WHERE a.id IN (SELECT p.article_id FROM PERMISSIONS p WHERE p.permission_type_id=1) or a.author_id=?;";
    /*"SELECT DISTINCT a.ID, a.AUTHOR_ID, a.NAME, a.TEXT  FROM ARTICLES a JOIN permissions p ON p.article_id=a.id WHERE" +
            " p.permission_type_id=1 or a.author_id=?";*/
    public static final String DELETE_ARTICLE = "DELETE FROM ARTICLES WHERE ID=?";
    public static final String CHANGE_ARTICLE = "UPDATE ARTICLES SET NAME=?, TEXT=? WHERE ID=?";
    public static final String GET_ALL_USERS = "SELECT * FROM USERS;";
    public static final String GET_ALL_PERMISSION_TYPES = "SELECT * FROM PERMISSIONS_TYPES;";
    public static final String ADD_PERMISSION = "INSERT INTO PERMISSIONS (USER_ID, ARTICLE_ID, PERMISSION_TYPE_ID) VALUES(?,?,?)";
    public static final String CHECK_PERMISSION = "SELECT COUNT(ID) FROM PERMISSIONS WHERE USER_ID=? AND ARTICLE_ID=? AND PERMISSION_TYPE_ID=?;";
    public static final String DELETE_PERMISSION = "DELETE FROM PERMISSIONS WHERE ARTICLE_ID=?";
    public NewsDAOImpl() {
    }

    public List getListArticle() throws SQLException, IOException, PropertyVetoException {
        ArrayList<Article> list = new ArrayList();
        PreparedStatement preparedStatement = DBConnector.getInstance().getConnection().prepareStatement(GET_ALL);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Article news = new Article();
            news.setId(rs.getInt(1));
            news.setAuthor(new User(Integer.parseInt(rs.getString(2))));
            news.setName(rs.getString(3));
            news.setText(rs.getString(4));
            list.add(news);
        }
        return list;
    }

    public List getArticlesListView(int id) throws SQLException, IOException, PropertyVetoException {
        ArrayList<Article> list = new ArrayList();
        PreparedStatement preparedStatement = DBConnector.getInstance().getConnection().prepareStatement(GET_ARTICLES_TO_VIEW);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Article news = new Article();
            news.setId(rs.getInt(1));
            news.setAuthor(getUserById(Integer.parseInt(rs.getString(2))));
            news.setName(rs.getString(3));
            news.setText(rs.getString(4));
            list.add(news);
        }
       // DBConnector.getInstance().getConnection().close();
        return list;
    }

    public Article getArticle(int id) throws SQLException, IOException, PropertyVetoException {
        Article article = new Article();
        PreparedStatement preparedStatement = DBConnector.getInstance().getConnection().prepareStatement(GET_ARTICLE);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            article.setId(rs.getInt(1));
            article.setAuthor(new User(Integer.parseInt(rs.getString(2))));
            article.setName(rs.getString(3));
            article.setText(rs.getString(4));
        }
        return article;
    }

    public int addArticle(Article article) throws SQLException, IOException, PropertyVetoException {
        PreparedStatement st = DBConnector.getInstance().getConnection().prepareStatement(ADD_ARTICLE, new String[]{"ID"});
        st.setInt(1, article.getAuthor().getId());
        st.setString(2, article.getName());
        st.setString(3, article.getText());
        st.executeUpdate();

        ResultSet resultSet = st.getGeneratedKeys();
        if (resultSet.next()) {
            int newsId = resultSet.getInt(1);
            System.out.println(newsId);
            return newsId;
        } else return -1;
    }

    public int addPermission(Integer userId, Integer articleId, Integer permissionTypeId) throws SQLException, IOException, PropertyVetoException {
        PreparedStatement st = DBConnector.getInstance().getConnection().prepareStatement(ADD_PERMISSION, new String[]{"ID"});
        st.setInt(1, userId);
        st.setInt(2, articleId);
        st.setInt(3, permissionTypeId);
        st.executeUpdate();

        ResultSet resultSet = st.getGeneratedKeys();
        if (resultSet.next()) {
            int newsId = resultSet.getInt(1);
            System.out.println(newsId);
            return newsId;
        } else return -1;
    }

    public void deleteArticle(int id) throws SQLException, IOException, PropertyVetoException {
        PreparedStatement st = DBConnector.getInstance().getConnection().prepareStatement(DELETE_PERMISSION);
        st.setInt(1, id);
        st.executeUpdate();
        PreparedStatement st1 = DBConnector.getInstance().getConnection().prepareStatement(DELETE_ARTICLE);
        st1.setInt(1, id);
        st1.executeUpdate();
    }

    public void changeArticle(Article article) throws SQLException, IOException, PropertyVetoException {
        PreparedStatement st = DBConnector.getInstance().getConnection().prepareStatement(CHANGE_ARTICLE);
        st.setString(1, article.getName());
        st.setString(2, article.getText());
        st.setInt(3, article.getId());
        st.executeUpdate();
    }

    private User getUserById(int id) throws SQLException, IOException, PropertyVetoException {
        User result = new User();
        PreparedStatement statement = DBConnector.getInstance().getConnection().prepareStatement("SELECT id, name, password FROM users WHERE id = ?;");
        statement.setInt(1, id);
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            result.setId(id);
            result.setName(set.getString("name"));
        }
        return result;
    }


    public List getAllUsers() throws SQLException, IOException, PropertyVetoException {
        ArrayList<User> list = new ArrayList();
        PreparedStatement preparedStatement = DBConnector.getInstance().getConnection().prepareStatement(GET_ALL_USERS);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            list.add(user);
        }
        return list;
    }

    public List getAllPermissionTypes() throws SQLException, IOException, PropertyVetoException {
        ArrayList<PermissionType> list = new ArrayList();
        PreparedStatement preparedStatement = DBConnector.getInstance().getConnection().prepareStatement(GET_ALL_PERMISSION_TYPES);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            PermissionType type = new PermissionType();
            type.setId(rs.getInt(1));
            type.setName(rs.getString(2));
            list.add(type);
        }
        return list;
    }

    public boolean checkPermission(Integer userId, Integer articleId, Integer permissionId) throws SQLException, IOException, PropertyVetoException {
        PreparedStatement preparedStatement = DBConnector.getInstance().getConnection().prepareStatement(CHECK_PERMISSION);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, articleId);
        preparedStatement.setInt(3, permissionId);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next())
            return rs.getInt(1) > 0;
        else
            return false;
    }

    public static void main(String args[]) throws SQLException, ClassNotFoundException, IOException, PropertyVetoException {
//        NewsDAOImpl dao= new NewsDAOImpl();
//
//        dao.addArticle(new News("English","Angelina",new Date(System.currentTimeMillis()),"Anyone who reads Old and Middle English literary texts will be familiar with the mid-brown volumes of the EETS, with the symbol of Alfred's jewel embossed on the front cover. Most of the works attributed to King Alfred or to Aelfric, along with some of those by bishop Wulfstan and much anonymous prose and verse from the pre-Conquest period, are to be found within the Society's three series; all of the surviving medieval drama, most of the Middle English romances, much religious and secular prose and verse including the English works of John Gower, Thomas Hoccleve and most of Caxton's prints all find their place in the publications. Without EETS editions, study of medieval English texts would hardly be possible.\n" +
//                "\n" +
//                "As its name states, EETS was begun as a 'club', and it retains certain features of that even now. It has no physical location, or even office, no paid staff or editors, but books in the Original Series are published in the first place to satisfy subscriptions paid by individuals or institutions. This means that there is need for a regular sequence of new editions, normally one or two per year; achieving that sequence can pose problems for the Editorial Secretary, who may have too few or too many texts ready for publication at any one time. Details on a separate sheet explain how individual (but not institutional) members can choose to take certain back volumes in place of the newly published volumes against their subscriptions. On the same sheet are given details about the very advantageous discount available to individual members on all back numbers. In 1970 a Supplementary Series was begun, a series which only appears occasionally (it currently has 24 volumes within it); some of these are new editions of texts earlier appearing in the main series.","http://localhost:8082/0.jpg"));

    }
}

