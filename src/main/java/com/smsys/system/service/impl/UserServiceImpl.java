package com.smsys.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.smsys.system.entity.User;
import com.smsys.system.mapper.UserMapper;
import com.smsys.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dlx
 * @since 2023-10-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> login(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        //查询用户
        //结果不为空，则生成token
        LambdaQueryWrapper<User> Wrapper = new LambdaQueryWrapper<>();
        Wrapper.eq(User::getUsername,user.getUsername());
        Wrapper.eq(User::getPassword,user.getPassword());
        User loginUser = this.baseMapper.selectOne(Wrapper);
        if(loginUser != null){
            //使用uuid
            String key = "user:" + UUID.randomUUID();
            System.out.println("=======The new key is=======");
            System.out.println("=============="+key+"==============");

            loginUser.setPassword(null);

            //存入session(已弃用
            //HttpSession LoginSession = request.getSession();
            //LoginSession.setAttribute(
            //        "LoginUser",LoginUser);
            //LoginSession.setAttribute("key",key);

            //生成cookie
            Cookie cookie_key = new Cookie("cookie_key",key);
            //设置cookie持久化时间：30分钟
            cookie_key.setMaxAge(30 * 60);
            //设置为当前项目全部带有此cookie
            cookie_key.setPath(request.getContextPath());
            //向客户端发送cookie
            response.addCookie(cookie_key);
            System.out.println("=======login has sent cookies=======");

            //存入redis
            loginUser.setPassword(null);
            redisTemplate.opsForValue().set(key,loginUser,30, TimeUnit.MINUTES);

            //返回数据
            Map<String, Object> data = new HashMap<>();
            data.put("token",key);
            return data;
        }
        return null;
    }



    @Override
    public Map<String, Object> getUserInfo(HttpServletRequest req, HttpServletResponse resp) {
        // 从请求中获取名为 "cookie_key" 的 Cookie
        Cookie[] cookies = req.getCookies();
        String token = null;
        User loginUser = null;

        if (cookies != null) {
            System.out.println("=======getUserInfo has received cookies=======");
            for (Cookie cookie : cookies) {
                if ("cookie_key".equals(cookie.getName())) {
                    System.out.println("=======getUserInfo has found the right cookies=======");
                    token = cookie.getValue();
                    System.out.println("=============="+token+"==============");
                    break;
                }
            }
        }

        if (token != null) {
            // 获取存储在Redis中的用户信息
            Object obj = redisTemplate.opsForValue().get(token);
            if(obj != null){
                System.out.println("=======getUserInfo has found the UserInfo in Redis=======");
                loginUser = JSON.parseObject(JSON.toJSONString(obj),User.class);
            }
            if (loginUser != null) {
                System.out.println("=======getUserInfo has found the Redis=======");
                Map<String, Object> data = new HashMap<>();
                data.put("name", loginUser.getUsername());
                data.put("role", loginUser.getRole());
                System.out.println("=======getUserInfo return the data=======");
                System.out.println("=============="+data+"==============");
                return data;
            }
        }
        return null;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response){
        // 从请求中获取名为 "cookie_key" 的 Cookie
        Cookie[] cookies = request.getCookies();
        String token = null;

        if (cookies != null) {
            System.out.println("=======getUserInfo has received cookies=======");
            for (Cookie cookie : cookies) {
                if ("cookie_key".equals(cookie.getName())) {
                    System.out.println("=======getUserInfo has found the right cookies=======");
                    token = cookie.getValue();
                    System.out.println("=============="+token+"==============");
                    break;
                }
            }
        }
        // 删除Redis里面的用户信息
        if(token != null) {
            redisTemplate.delete(token);
        }
        // 保存cookie，实现自动登录
        Cookie cookie_username = new Cookie("cookie_key", "");
        // 设置cookie的持久化时间，0
        cookie_username.setMaxAge(0);
        // 设置为当前项目下都携带这个cookie
        cookie_username.setPath(request.getContextPath());
        // 向客户端发送cookie
        response.addCookie(cookie_username);
    }


}
