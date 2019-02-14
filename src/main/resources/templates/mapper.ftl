package ${mapperClassPath};

import org.apache.ibatis.annotations.Mapper;
import ${modelClassPath}.${genContent.className};
import java.util.List;
import org.apache.ibatis.annotations.Delete;

@Mapper
public interface ${genContent.className}Mapper {
    int add${genContent.className}(${genContent.className} ${genContent.varName});
    int update${genContent.className}(${genContent.className} ${genContent.varName});
    @Delete("delete from ${genContent.tableName} where <#list genContent.genColumns as column><#if column.isPK==1>${column.column}=${r'#{'}${column.modelColumn}${r'}'}</#if></#list>")
    int delete${genContent.className}(<#list genContent.genColumns as column><#if column.isPK==1>${column.columnJavaType} ${column.modelColumn}</#if></#list>);
    List<${genContent.className}> list${genContent.className}(${genContent.className} ${genContent.varName});
}