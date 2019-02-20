package com.skill.dao;

import org.apache.ibatis.annotations.Mapper;
import com.skill.model.SeckillOrder;
import java.util.List;
import org.apache.ibatis.annotations.Delete;

@Mapper
public interface SeckillOrderMapper {
    int addSeckillOrder(SeckillOrder seckillOrder);
    int updateSeckillOrder(SeckillOrder seckillOrder);
    @Delete("delete from seckill_order where id=#{id}")
    int deleteSeckillOrder(long id);
    List<SeckillOrder> listSeckillOrder(SeckillOrder seckillOrder);
}