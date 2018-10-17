package com.xmasworking.tis.controller.system;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/8/31 - 下午4:29
 * Created by IntelliJ IDEA.
 */
@Controller
@RequestMapping("/system")
public class LoginController {
    private final String MainURL = "/system/home";

    @GetMapping("/")
    public String index() {
        return "system/index";
    }

    @GetMapping("/login")
    public String login() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            return "redirect:"+MainURL;
        }
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> loginPost(String username, String password, boolean rememberMe) {
        Map<String, Object> map = new HashMap<>();
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();

        String error = "";
        try {
            usernamePasswordToken.setRememberMe(rememberMe);
            //完成登录
            subject.login(usernamePasswordToken);

            map.put("forword", MainURL);
            map.put("status", true);
            map.put("error", "登录成功！");
            //此处会被Shiro重定向至success页面
            return map;

        } catch (UnknownAccountException uae) {
            error = "用户不存在,请申请用户!!!";
        } catch (IncorrectCredentialsException ice) {
            error = "用户名密码错误,请确认后重新登陆!!!";
        } catch (LockedAccountException lae) {
            error = "账户已被锁定，无法登陆!!!";
        } catch (AuthenticationException ae) {
            error = "unexpected condition...";
        } catch(Exception e) {
            error = "登录异常：" + e.getMessage();
        }

        map.put("status", false);
        map.put("error", error);
        return map;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        return "redirect:/login";
    }

    @RequestMapping(value = "/loginSuccess")
    @ResponseBody
    public Map loginSuccess() {
        Map map = new HashMap(16);
        map.put("forword", MainURL);
        map.put("status", true);
        map.put("error", "登录成功");
        return map;
    }

}