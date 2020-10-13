package com.gql.controller;

import com.gql.captcha.utils.CaptchaUtil;
import com.gql.common.core.SpringSecurityContext;
import com.gql.entity.UserInfoEntity;
import com.gql.response.Result;
import com.gql.service.SysMenusService;
import com.gql.service.SysUserInfoService;
import com.gql.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author gql
 * @date 2020/8/1
 **/
@Controller
public class LoginController {

    @Autowired
    private SysUserInfoService sysUserInfoService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private SysMenusService sysMenusService;


    @GetMapping(value = {"/","/login"})
    public String getLogin(){

        return "login";
    }
    @GetMapping(value ="/api/index")
    public String getIndex(){
        return "index";
    }

   @GetMapping(value ="/api/initindex")
    public ModelAndView getInitIndex(){
        // ModelMap modelAndView = new ModelMap();
       ModelAndView modelAndView = new ModelAndView();
        UserDetails principal = SpringSecurityContext.getPrincipal();
        List<Map<String, Object>> maps = null;

        if (principal != null) {
            maps = sysMenusService.sysMenusName(principal.getUsername());
            modelAndView.addObject("maps",maps);
            modelAndView.addObject("username", principal.getUsername());
            modelAndView.setViewName("index");
        }
            return modelAndView;
    }



    @PostMapping("/user/login")
    @ResponseBody
    public Result getLogin(@RequestParam("username") String username,@RequestParam String password,@RequestParam String validataCode, HttpServletRequest request){
        try {
            boolean ver = CaptchaUtil.ver(validataCode, request);
           if (!ver){
                return Result.error("验证码错误！");
            }
            return  sysUserInfoService.login(username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }


    @PostMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                new SecurityContextLogoutHandler().logout(request,response,authentication);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return "login";

    }

    @PostMapping(value = "/register")
    public Result register(@RequestBody UserInfoEntity userInfoEntity){
        try {
            sysUserInfoService.register(userInfoEntity);
        }catch (Exception e){
            return Result.error();
        }
        return Result.success();
    }

    @GetMapping(value = "/validata/code")
    public void  register(HttpServletResponse response, HttpServletRequest request, HttpSession session){
        try {

            CaptchaUtil.out(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
