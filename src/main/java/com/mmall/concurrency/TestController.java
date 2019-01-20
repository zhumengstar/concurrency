package com.mmall.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:zhumeng
 * @desc:
 **/
@Controller
@Slf4j
@ResponseBody
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
