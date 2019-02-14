package com.skill.common.config;//package com.jie.test.common.config;
//
//import com.alibaba.fastjson.JSONException;
//import com.alibaba.fastjson.JSONObject;
//import com.csj.linkorder.common.exception.BusinessException;
//import com.csj.linkorder.common.exception.SysErrorEnums;
//import com.csj.linkorder.modules.sys.service.SystemService;
//import com.csj.linkorder.modules.usertoken.dao.UserTokenMapper;
//import com.csj.linkorder.modules.usertoken.entity.UserToken;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Aspect
//@Component
//public class ApiAspect {
//
//    public static Logger logger = LoggerFactory.getLogger(ApiAspect.class);
//
//    @Autowired
//    private SystemService systemService;
//    @Autowired
//    private UserTokenMapper userTokenMapper;
//
////    @Pointcut("execution(* com.csj.linkorder.modules.appcontroller.*(..))")
//    @Pointcut("execution(* com.csj.linkorder.modules.appcontroller.*.*(..))")
//    public void pointCut() {
//    }
//
//    @Before("pointCut()")
//    public void beforeOperation(JoinPoint point) {
//        String methodName = point.getSignature().getName();
//        Object[] argArrray = point.getArgs();
//        if (!(methodName.equals("login"))) {
//            logger.debug("@Before：目标方法为：" + methodName);
//            logger.debug("@Before：参数为：" + Arrays.toString(argArrray));
//            logger.debug("@Before：织入对象：" + point.getTarget());
//            try {
//                JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(argArrray[0]));
//                String token = jsonObject.get("token") == null ? "" : jsonObject.get("token").toString();
//                if (StringUtils.isEmpty(token)) {
//                    throw new BusinessException(SysErrorEnums.NOT_NULL_TOKEN);
//                }
//                if (isExpired(token)) {
//                    throw new BusinessException(SysErrorEnums.EXPIRED_TOKEN);
//                }
//            } catch (JSONException e) {
//                throw new BusinessException(SysErrorEnums.ERROR_PARAMS);
//            }
//        }
//    }
//
//    /**
//     * 判断token是否过期
//     *
//     * @param token
//     * @return
//     */
//    private boolean isExpired(String token) {
////        User user = new User();
////        user.setToken(token);
//        UserToken tk=userTokenMapper.getUserTokenByToken(token);
////        List<User> userList = systemService.findUser(user);
////        if (userList.size() != 1) {
////            return true;
////        }
//        Long expiredTime = System.currentTimeMillis()-Long.valueOf(tk.getCreateDate().getTime());
//        if (expiredTime<36000) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//}
