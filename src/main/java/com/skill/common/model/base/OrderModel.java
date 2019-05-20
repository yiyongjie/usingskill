package com.skill.common.model.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderModel implements Serializable {

    private static final long serialVersionUID = -6688579105045318839L;

    private String columnName;
    private String sort;

    public static OrderModel asc(String columnName){
        return new OrderModel(columnName,OrderType.ASC.name());
    }

    public static OrderModel desc(String columnName){
        return new OrderModel(columnName,OrderType.DESC.name());
    }

    public OrderModel(String columnName, String sort) {
        this.columnName = columnName;
        this.sort = sort;
    }

    public static enum OrderType {
        ASC,
        DESC;
    }
}
