<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${mapperClassPath}.${genContent.className}Mapper" >
  <resultMap id="BaseResultMap" type="${voClassPath}.${genContent.voName}" >
        <#list genContent.genColumns as column>
            <#if column.isPK==1>
            <id column="${column.column}" property="${column.modelColumn}" jdbcType="${column.columnType}" />
            <#else>
            <result column="${column.column}" property="${column.modelColumn}" jdbcType="${column.columnType}" />
            </#if>
        </#list>
  </resultMap>

    <sql id="Base_Column_List" >
        <#list genContent.genColumns as column><#if column_index=0>${column.column}<#else>,${column.column}</#if></#list>
    </sql>

    <sql id="Base_Where">
        <where>
             <#list genContent.genColumns as column>
            <#if column.isPK!=1>
            <if test="${column.modelColumn} != null and ${column.modelColumn} != ''" >
                ${column.modelColumn}=${r'#{'}${column.modelColumn},jdbcType=${column.columnType}${r'}'},
            </if>
            </#if>
             </#list>
        </where>
    </sql>

    <insert id="add${genContent.className}" keyProperty="id" useGeneratedKeys="true">
        insert into ${genContent.tableName}
        <trim prefix="(" suffix=")" suffixOverrides="," >
            <#list genContent.genColumns as column>
                <#if column.isPK!=1>
                <if test="${column.modelColumn} != null and ${column.modelColumn} != ''" >
                    ${column.column},
                </if>
                </#if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides="," >
              <#list genContent.genColumns as column>
                  <#if column.isPK!=1>
                  <if test="${column.modelColumn} != null and ${column.modelColumn} != ''" >
                      ${r'#{'}${column.modelColumn},jdbcType=${column.columnType}${r'}'},
                  </if>
                  </#if>
              </#list>
        </trim>
    </insert>

    <update id="update${genContent.className}" parameterType="${modelClassPath}.${genContent.className}">
        update ${genContent.tableName}
        <set>
            <#list genContent.genColumns as column>
            <#if column.isPK!=1>
            <if test="${column.modelColumn} != null and ${column.modelColumn} != ''" >
            ${column.modelColumn}=${r'#{'}${column.modelColumn},jdbcType=${column.columnType}${r'}'},
            </if>
            </#if>
            </#list>
        </set>
        where <#list genContent.genColumns as column><#if column.isPK==1>${column.modelColumn}=${r'#{'}${column.modelColumn},jdbcType=${column.columnType}${r'}'}</#if></#list>
    </update>

    <select id="list${genContent.className}" resultMap="BaseResultMap" parameterType="${modelClassPath}.${genContent.className}">
        select <include refid="Base_Column_List"/> from ${genContent.tableName}
        <where>
            <include refid="Base_Where" />
        </where>
        <if test="orderBy != null and !orderBy.isEmpty()">
            order by
            <foreach collection="orderBy" item="orderByItem" separator=",">
            ${r'${'}orderByItem.columnName${r'}'}  ${r'${'}orderByItem.sort${r'}'}
            </foreach>
        </if>
    </select>

    <select id="count${genContent.className}" resultMap="BaseResultMap" parameterType="${modelClassPath}.${genContent.className}">
        select count(1) from ${genContent.tableName}
        <where>
            <include refid="Base_Where" />
        </where>
    </select>
</mapper>