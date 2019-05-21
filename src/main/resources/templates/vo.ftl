package ${voClassPath};
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import com.skill.common.model.base.BaseModel;
import java.io.Serializable;

@Data
@ApiModel(value = "${genContent.tableComment}")
public class ${genContent.className}VO implements Serializable{

   private static final long serialVersionUID = -1L;
<#list genContent.genColumns as column>
   /*
   *${column.columnRemark}
   */
   @ApiModelProperty(value = "${column.columnRemark}")
   private ${column.columnJavaType} ${column.modelColumn};
</#list>
}