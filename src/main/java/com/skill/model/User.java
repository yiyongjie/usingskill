package com.skill.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.skill.common.model.base.BaseModel;

@Data
@ApiModel(value = "")
public class User extends BaseModel {

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