package com.skill.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "")
public class User {

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