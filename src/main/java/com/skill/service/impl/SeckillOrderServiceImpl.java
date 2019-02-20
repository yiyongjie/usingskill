package com.skill.service.impl;

import com.skill.dao.SeckillOrderMapper;
import com.skill.model.SeckillOrder;
import com.skill.service.SeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeckillOrderServiceImpl implements SeckillOrderService {

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;
    @Override
    public List<SeckillOrder> listSeckillOrder(SeckillOrder seckillOrder) {
        return seckillOrderMapper.listSeckillOrder(seckillOrder);
    }
}
