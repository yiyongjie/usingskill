package ${modelClassPath};
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "${genContent.tableComment}")
public class ${genContent.className} {

<#list genContent.genColumns as column>
   /*
   *${column.columnRemark}
   */
   @ApiModelProperty(value = "${column.columnRemark}")
   private ${column.columnJavaType} ${column.modelColumn};
</#list>
}