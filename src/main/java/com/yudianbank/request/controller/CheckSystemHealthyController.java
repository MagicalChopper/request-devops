package com.yudianbank.request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("request")
public class CheckSystemHealthyController {

    /**
     * 用于外部调用，检查本系统是否正常运作
     * @return String
     */
    @RequestMapping("/isHealthy")
    @ResponseBody
    String isHealthy() {
        return "request-devops project is running healthy.";
    }

}
