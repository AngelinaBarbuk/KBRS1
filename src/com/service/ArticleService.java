package com.service;

import com.beans.Article;
import com.beans.PermissionType;
import com.beans.User;
import com.dao.dao.NewsDAO;
import com.dao.dao.NewsDAOImpl;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by VVV on 26.07.2016.
 */
public class ArticleService {
    private NewsDAO<Article> dao;
    private static ArticleService newsService;

    private ArticleService() {
        dao = new NewsDAOImpl();
    }

    public static ArticleService getInstance() {
        if (newsService == null) {
            newsService = new ArticleService();
        }
            return newsService;
    }

    public int addArticle(Article article) throws SQLException, IOException, PropertyVetoException {
        return dao.addArticle(article);
    }

    public int addPermission(Integer userId, Integer articleId, Integer permissionTypeId) throws SQLException, IOException, PropertyVetoException{
        return dao.addPermission(userId,articleId,permissionTypeId);
    }
    public void deleteArticle(int id) throws SQLException, IOException, PropertyVetoException {
        dao.deleteArticle(id);
    }


    public List<Article> getArticleList() throws PropertyVetoException, SQLException, IOException {
        return dao.getListArticle();
    }

    public List<Article> getArticlesListView(int id) throws PropertyVetoException, SQLException, IOException {
        return dao.getArticlesListView(id);
    }


    public Article getArticle(int id) throws PropertyVetoException, SQLException, IOException {
        return dao.getArticle(id);
    }
    public void editArticle(Article article) throws PropertyVetoException, SQLException, IOException {
        dao.changeArticle(article);
    }

    public List<User> getAllUsers() throws PropertyVetoException, SQLException, IOException {
        return dao.getAllUsers();
    }

    public List<PermissionType> getAllPermissionTypes() throws PropertyVetoException, SQLException, IOException {
        return dao.getAllPermissionTypes();
    }

    public boolean checkPermission(Integer userId, Integer articleId, Integer permissionId) throws SQLException, IOException, PropertyVetoException {
       return dao.checkPermission(userId, articleId, permissionId);
    }

}
