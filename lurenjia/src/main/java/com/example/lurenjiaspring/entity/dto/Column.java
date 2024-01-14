package com.example.lurenjiaspring.entity.dto;

public class Column {
    private String columnName;
    private String columnType;
    private String typeConstraint;
    private String defaultValue;
    private boolean notNull;
    private String comment;

    public Column(String columnName, String columnType, String typeConstraint, String defaultValue, boolean notNull, String comment) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.typeConstraint = typeConstraint;
        this.defaultValue = defaultValue;
        this.notNull = notNull;
        this.comment = comment;
    }
}
