package com.xmasworking.tis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/8/30 - 上午8:58
 * Created by IntelliJ IDEA.
 */
@RestController
public class ShowpageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowpageController.class);

    @RequestMapping(value = "/hi")
    public String getHi(){
        return "hi my world";
    }
}
