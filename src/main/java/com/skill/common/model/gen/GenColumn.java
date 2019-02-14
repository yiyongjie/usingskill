package com.skill.common.model.gen;

import lombok.Data;

/**
 * 生成对象字段模板
 */
@Data
public class GenColumn {
    /**
     * 字段名称
     */
    private String column;
    /**
     * 生成模板字段
     */
    private String modelColumn;
    /**
     * 数据库的字段类型
     */
    private String columnType;
    /**
     * 字段注释
     */
    private String columnRemark;
    /**
     * java的字段类型
     */
    private String columnJavaType;
    /**
     * 是否是主键
     */
    private int isPK;
}
