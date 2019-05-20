package ${modelClassPath};
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import com.skill.common.model.base.BaseModel;

@Data
@ApiModel(value = "${genContent.tableComment}")
public class ${genContent.className}VO extends BaseModel{

<#list genContent.genColumns as column>
   /*
   *${column.columnRemark}
   */
   @ApiModelProperty(value = "${column.columnRemark}")
   private ${column.columnJavaType} ${column.modelColumn};
</#list>
}