package com.skill.common.model.gen;

import lombok.Data;

import java.util.List;


/**
 * 生成对象模板参数
 */
@Data
public class GenContent {
    /**
     * 类名
     */
    private String className;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 变量名称
     */
    private String varName;
    /**
     * 表注释
     */
    private String tableComment;
    /**
     * 字段属性
     */
    private List<GenColumn> genColumns;
    /**
     * dto
     */
    private String dtoName;
    /**
     * vo
     */
    private String voName;
}
