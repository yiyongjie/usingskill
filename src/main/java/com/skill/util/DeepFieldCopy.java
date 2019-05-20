package com.skill.util;

import com.alibaba.fastjson.JSON;

/**
 * 对象转换工具
 */
public abstract class DeepFieldCopy {
    public static <FromType, ToType> ToType transform(FromType sourceObject, Class<ToType> resultClass) {
        return JSON.parseObject(JSON.toJSONString(sourceObject), resultClass);
    }
}
