package com.skill.model.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.skill.common.model.base.BaseModel;

@Data
@ApiModel(value = "")
public class UserDTO extends BaseModel implements Serializable {

   private static final long serialVersionUID = -1L;
   /*
   *id
   */
   @ApiModelProperty(value = "id")
   private int id;
   /*
   *名称
   */
   @ApiModelProperty(value = "名称")
   private String name;
}