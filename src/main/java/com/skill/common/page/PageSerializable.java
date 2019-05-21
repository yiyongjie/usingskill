package com.skill.common.page;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel
public class PageSerializable<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "总数")
    protected long total;
    @ApiModelProperty(value = "数据")
    protected List<T> list;

    public PageSerializable() {
    }

    public PageSerializable(List<T> list) {
        this.list = list;
        if (list instanceof Page) {
            this.total = ((Page)list).getTotal();
        } else {
            this.total = (long)list.size();
        }

    }

    public static <T> com.github.pagehelper.PageSerializable<T> of(List<T> list) {
        return new com.github.pagehelper.PageSerializable(list);
    }

    public String toString() {
        return "PageSerializable{total=" + this.total + ", list=" + this.list + '}';
    }
}
