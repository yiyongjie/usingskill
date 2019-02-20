package com.skill.controller;

import com.skill.common.model.APIResponse;
import com.skill.model.SeckillOrder;
import com.skill.service.SeckillOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Api(value = "TestController",description = "测试用的玩意")
@RequestMapping(value = "/test/*")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SeckillOrderService seckillOrderService;

    @RequestMapping(value = "testone",method = RequestMethod.POST)
    @ApiOperation(value = "测试先")
    public APIResponse test(@RequestBody SeckillOrder seckillOrder){
        List<SeckillOrder> list=seckillOrderService.listSeckillOrder(seckillOrder);
        return APIResponse.success(list);
    }
}
