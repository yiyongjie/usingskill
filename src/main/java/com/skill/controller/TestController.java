//package com.skill.controller;
//
//import com.github.pagehelper.PageInfo;
//import com.skill.common.model.APIResponse;
//import com.skill.model.User;
//import com.skill.service.UserService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//@RestController
//@Api(value = "TestController",description = "测试用的玩意")
//@RequestMapping(value = "/test/*")
//public class TestController {
//
//    @Autowired
//    private RestTemplate restTemplate;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @RequestMapping(value = "testDBSelect",method = RequestMethod.POST)
//    @ApiOperation(value = "测试数据库查询")
//    public APIResponse<List<User>> testSelect(@RequestBody User user){
//        List<User> list=userService.listUser(user);
//        return APIResponse.success(list);
//    }
//
//    @RequestMapping(value = "testDBPage",method = RequestMethod.POST)
//    @ApiOperation(value = "测试数据库分页,排序查询")
//    public APIResponse<PageInfo<User>> testPage(@RequestBody User user){
//        PageInfo<User> list=userService.pageUser(user);
//        return APIResponse.success(list);
//    }
//
//    @RequestMapping(value = "testRedis",method = RequestMethod.GET)
//    @ApiOperation(value = "测试redis")
//    public APIResponse testRedis(){
//        APIResponse apiResponse=new APIResponse();
//        apiResponse.setCode("200");
//        apiResponse.setMsg("成功");
//        String a=stringRedisTemplate.opsForValue().get("name");
//        apiResponse.setData(a);
//        return apiResponse;
//    }
//
//    @RequestMapping(value = "testHttp",method = RequestMethod.GET)
//    @ApiOperation(value = "测试http")
//    public APIResponse testHttp(){
//        User user=new User();
//        user.setId(1);
//        ResponseEntity<User> responseEntity=restTemplate.postForEntity("http://127.0.0.1:8080/test/testDB",user,User.class);
//        return APIResponse.success(responseEntity.getBody());
//    }
//}
