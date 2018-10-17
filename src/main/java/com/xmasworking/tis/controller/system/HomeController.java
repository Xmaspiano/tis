package com.xmasworking.tis.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/9/1 - 下午12:11
 * Created by IntelliJ IDEA.
 */
@Controller
@RequestMapping("/system/home")
public class HomeController {

    @RequestMapping("")
    public String index(){
        return "system/home";
    }
}
