package com.smsys.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.smsys.system.entity.User;
import com.smsys.system.mapper.UserMapper;
import com.smsys.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    //@Autowired
    //private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> login(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        //查询用户
        //结果不为空，则生成token
        LambdaQueryWrapper<User> Wrapper = new LambdaQueryWrapper<>();
        Wrapper.eq(User::getUsername,user.getUsername());
        Wrapper.eq(User::getPassword,user.getPassword());
        User LoginUser = this.baseMapper.selectOne(Wrapper);
        if(LoginUser != null){
            //使用uuid
            String key = "user:" + UUID.randomUUID();
            System.out.println("=======The new key is=======");
            System.out.println("=============="+key+"==============");

            LoginUser.setPassword(null);
            //存入session
            HttpSession LoginSession = request.getSession();
            LoginSession.setAttribute(
                    "LoginUser",LoginUser);
            LoginSession.setAttribute("key",key);
            //生成cookie
            Cookie cookie_key = new Cookie("cookie_key",key);
            //设置cookie持久化时间：30分钟
            cookie_key.setMaxAge(30 * 60);
            //设置为当前项目全部带有此cookie
            cookie_key.setPath(request.getContextPath());
            //向客户端发送cookie
            response.addCookie(cookie_key);
            System.out.println("=======login has sent cookies=======");

            //存入redis（已禁用
            //redisTemplate.opsForValue().set(key,loginUser,30, TimeUnit.MINUTES);

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
            // 获取存储在 Session 中的用户信息
            System.out.println("=======getUserInfo has received token=======");
            HttpSession session = req.getSession();
            User loginUser = (User) session.getAttribute("LoginUser");

            if (loginUser != null) {
                System.out.println("=======getUserInfo has found the session=======");
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
    public void logout(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        // 删除session里面的用户信息
        session.removeAttribute("LoginUser");
        session.removeAttribute("key");
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
