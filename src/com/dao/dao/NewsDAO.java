package com.dao.dao;

import com.beans.Article;
import com.beans.PermissionType;
import com.beans.User;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by VVV on 13.07.2016.
 */
public interface NewsDAO<T> {

    List<T> getListArticle() throws SQLException, IOException, PropertyVetoException;
    List<T> getArticlesListView(int id) throws SQLException, IOException, PropertyVetoException;
    Article getArticle(int id) throws SQLException, IOException, PropertyVetoException;
    int addArticle(T news) throws SQLException, IOException, PropertyVetoException;
    void deleteArticle(int id) throws SQLException, IOException, PropertyVetoException;
    void changeArticle(T news) throws SQLException, IOException, PropertyVetoException;
    List<User> getAllUsers() throws SQLException, IOException, PropertyVetoException;
    List<PermissionType> getAllPermissionTypes() throws SQLException, IOException, PropertyVetoException;
    int addPermission(Integer userId, Integer articleId, Integer permissionTypeId) throws SQLException, IOException, PropertyVetoException;
    boolean checkPermission(Integer userId, Integer articleId, Integer permissionId) throws SQLException, IOException, PropertyVetoException;

}
