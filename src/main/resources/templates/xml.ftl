<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${mapperClassPath}.${genContent.className}Mapper" >
  <resultMap id="BaseResultMap" type="${modelClassPath}.${genContent.className}" >
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
    </select>
</mapper>