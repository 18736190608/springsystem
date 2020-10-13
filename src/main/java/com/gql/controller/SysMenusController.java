package com.gql.controller;

import com.gql.common.core.SpringSecurityContext;
import com.gql.service.SysMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author gql
 * @date 2020/8/13
 **/
@Controller
public class SysMenusController {

    @Autowired
    private SysMenusService sysMenusService;

    //@PostMapping("/sys/rolemenu")
    public ModelAndView getSysRoleMenus(){
        UserDetails principal = SpringSecurityContext.getPrincipal();


        List<Map<String, Object>> maps = null;
        try {
            if (principal != null){
                maps = sysMenusService.sysMenusName(principal.getUsername());
            }
        }catch (Exception e){
        }
        return null;
    }
}
