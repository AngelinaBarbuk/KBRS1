package com.beans;

import java.util.ArrayList;
import java.util.Map;

public class Article {
    private Integer id;
    private String name;
    private User author;
    private String text;
   // Map<Integer, ArrayList<PermissionType>> permissions;

    public Article() {
    }
    public Article(User author, String name, String text) {
        this.author = author;
        this.name = name;
        this.text = text;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

 /*   public Map<Integer, ArrayList<PermissionType>> getPermissions() {
        return permissions;
    }

    public void setPermissions(Map<Integer, ArrayList<PermissionType>> permissions) {
        this.permissions = permissions;
    }*/

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", text='" + text + '\'' +
               // ", permissions=" + permissions +
                '}';
    }
}
