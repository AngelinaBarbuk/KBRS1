package com.beans;

import java.util.ArrayList;
import java.util.Map;

public class Article {
    private Integer id;
    private Integer authorId;
    private String name;
    private String text;
    Map<Integer, ArrayList<PermissionTypes>> permissions;

    public Article() {
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Article(Integer authorId, String name, String text) {
        this.authorId = authorId;
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

    public Map<Integer, ArrayList<PermissionTypes>> getPermissions() {
        return permissions;
    }

    public void setPermissions(Map<Integer, ArrayList<PermissionTypes>> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
