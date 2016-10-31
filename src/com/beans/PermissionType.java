package com.beans;

public class PermissionType {
    private Integer id;
    private String name;

    public PermissionType() {
    }

    public PermissionType(Integer id) {
        this.id = id;
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

    /*READ, DELETE;

    public static PermissionType getById(int id) {
        for (PermissionType e : values()) {
            if (e.ordinal() == id - 1) return e;
        }
        return null;
    }*/
}
