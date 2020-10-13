package com.gql.controller;

import com.gql.service.SysUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author gql
 * @date 2020/8/17
 **/
@Controller
public class SysUserOprationController {
    private static Logger logger = LoggerFactory.getLogger(SysUserOprationController.class);
    @Autowired
    private SysUserInfoService sysUserInfoService;



    @GetMapping("/api/useropration")
    public ModelAndView getAllUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("useropration");
        return modelAndView;
    }


    @PostMapping("/api/sysUser/")
    public ModelAndView getAlluser(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}
