package com.skill.controller;

import com.skill.common.model.APIResponse;
import com.skill.model.SeckillOrder;
import com.skill.service.SeckillOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "TestController",description = "测试用的玩意")
@RequestMapping(value = "/test/*")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SeckillOrderService seckillOrderService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "testDB",method = RequestMethod.POST)
    @ApiOperation(value = "测试数据库")
    public APIResponse<List<SeckillOrder>> test(@RequestBody SeckillOrder seckillOrder){
        List<SeckillOrder> list=seckillOrderService.listSeckillOrder(seckillOrder);
        return APIResponse.success(list);
    }

    @RequestMapping(value = "testRedis",method = RequestMethod.GET)
    @ApiOperation(value = "测试redis")
    public APIResponse testRedis(){
        APIResponse apiResponse=new APIResponse();
        apiResponse.setCode("200");
        apiResponse.setMsg("成功");
        String a=stringRedisTemplate.opsForValue().get("name");
        apiResponse.setData(a);
        return apiResponse;
    }

    @RequestMapping(value = "testHttp",method = RequestMethod.GET)
    @ApiOperation(value = "测试http")
    public APIResponse testHttp(){
        SeckillOrder seckillOrder=new SeckillOrder();
        seckillOrder.setId(1L);
        ResponseEntity<SeckillOrder> responseEntity=restTemplate.postForEntity("http://127.0.0.1:8080/test/testDB",seckillOrder,SeckillOrder.class);
        return APIResponse.success(responseEntity.getBody());
    }
}
