package com.skill.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "")
public class SeckillOrder {

   /*
   *主键
   */
   @ApiModelProperty(value = "主键")
   private long id;
   /*
   *购买者
   */
   @ApiModelProperty(value = "购买者")
   private String custName;
   /*
   *订单创建时间
   */
   @ApiModelProperty(value = "订单创建时间")
   private Date createTime;
}