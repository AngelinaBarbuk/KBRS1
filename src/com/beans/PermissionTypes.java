package com.beans;

public enum PermissionTypes {
    READ, EDIT;

    public static PermissionTypes getById(int id) {
        for (PermissionTypes e : values()) {
            if (e.ordinal() == id - 1) return e;
        }
        return null;
    }
}
