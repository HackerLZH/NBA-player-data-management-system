package com.lzh.graduationdesign.controller;

import com.lzh.graduationdesign.entity.User;
import com.lzh.graduationdesign.service.IUserService;
import com.lzh.graduationdesign.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName NavigatioinController
 * @Author HackerLZH
 * @Date 2022/4/10 19:53
 * @Description
 */
@Controller
public class NavigationController {
    @Autowired
    private IUserService userService;

    @GetMapping("/navigation")
    public String navigate(HttpServletRequest request,Map<String, User> map){
        User user = userService.getByToken(TokenUtil.getCookieToken(request));
        map.put("user", user);
        return "navigation";
    }
}
