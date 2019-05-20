package com.skill.common.model.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@ApiModel
public abstract class BaseModel implements Serializable {

    private static final long serialVersionUID = -2059071981004742580L;
    @ApiModelProperty(value = "查询开始时间")
    private Date queryBeginDate;
    @ApiModelProperty(value = "查询结束时间")
    private Date queryEndDate;
    private List<OrderModel> orderBy;
    @ApiModelProperty(value = "当前页数")
    private Integer pageNum;
    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;
}
