package com.skill.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * 对象转换工具
 */
public abstract class DeepFieldCopy {
    public static <FromType, ToType> ToType transform(FromType sourceObject, Class<ToType> resultClass) {
        return JSON.parseObject(JSON.toJSONString(sourceObject), resultClass);
    }

    public static <FromType, ToType> List<ToType> transformList(List<FromType> sourceList, Class<ToType> resultClass) {
        return JSON.parseArray(JSON.toJSONString(sourceList), resultClass);
    }
}
