package com.lzh.graduationdesign.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lzh.graduationdesign.entity.CodeMsg;
import com.lzh.graduationdesign.entity.Comment;
import com.lzh.graduationdesign.entity.Result;
import com.lzh.graduationdesign.entity.User;
import com.lzh.graduationdesign.exception.GlobalException;
import com.lzh.graduationdesign.mapper.CommentMapper;
import com.lzh.graduationdesign.service.IUserService;
import com.lzh.graduationdesign.service.MailService;
import com.lzh.graduationdesign.utils.RedisUtil;
import com.lzh.graduationdesign.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author HackerLZH
 * @since 2022-04-04
 */
@Api(value = "user controller")
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 注册
     *
     * @return com.lzh.graduationdesign.entity.Result<java.lang.String>
     * @Param [username, password, checkPassword]
     */
    @ApiOperation(value = "用户注册表单提交接口")
    @PostMapping("/doRegister")
    public String doRegister(@ApiParam(value = "username", required = true) @RequestParam("username") String username,
                             @ApiParam(value = "email", required = true) @RequestParam("email") String email,
                             @ApiParam(value = "password", required = true) @RequestParam("password") String password) {
        User user = new User();
        user.setName(username);
        user.setEmail(email);
        user.setPwd(password);
        user.setRegisterTime(LocalDate.now());
        user.setIcon("/assets/images/default-icon.png");
        userService.save(user);
        log.warn("注册成功：username={},email={},password={}", username, email, password);
        return "redirect:/index";
    }

    @ApiOperation(value = "校验网名是否注册过")
    @GetMapping("/register/checkName")
    @ResponseBody
    public Result<String> checkName(@ApiParam(value = "username", required = true) @RequestParam("username") String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return Result.info(CodeMsg.USERNAME_NOT_EXIST);
        }
        return Result.info(CodeMsg.USERNAME_EXISTED);
    }

    @ApiOperation(value = "检查验证码是否过期")
    @GetMapping("/register/checkCode")
    @ResponseBody
    public Result<String> checkCode(@RequestParam String code) {
        String host = null;
        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String localCode = redisUtil.get(host + "-code");
        if (!localCode.equals(code)){
            return Result.info(CodeMsg.FAILED);
        }
        return Result.info(CodeMsg.SUCCESS);
    }

    @ApiOperation(value = "发送邮件获取验证码")
    @GetMapping("/mail/sendCode")
    @ResponseBody
    public Result<String> sendCode(@RequestParam String email){
        String code = UUID.randomUUID().toString().toUpperCase();
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            redisUtil.set(host + "-code", code, 180);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        mailService.send(email, "NBA数据管理系统的验证码邮件", code);
        return Result.info(CodeMsg.SUCCESS);
    }

    /**
     * 登录功能
     * 验证用户名和密码，登录成功，生成token，存入到redis中
     * 登录成功
     */
    @ApiOperation(value = "用户登录表单接口")
    @PostMapping("/doLogin")
    public String doLogin(HttpServletResponse response, @ApiParam(value = "email", required = true) @RequestParam String email) {
        String token = userService.login(response, email);
        log.warn("登录成功-->token:{}", token);
        return "redirect:/navigation";
    }

    @ApiOperation(value = "校验邮箱是否注册过")
    @GetMapping("/login/checkEmail")
    @ResponseBody
    public Result<String> checkEmail(@RequestParam String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return Result.info(CodeMsg.EMAIL_NOT_REGISTERED);
        }
        return Result.info(CodeMsg.EMAIL_REGISTERED);
    }

    @ApiOperation(value = "校验登录密码是否正确")
    @GetMapping("/login/checkPassword")
    @ResponseBody
    public Result<String> checkPassword(@RequestParam String email, @RequestParam String password) {
        User user = userService.findByEmail(email);
        if (!user.getPwd().equals(password)) {
            return Result.info(CodeMsg.PASSWORD_ERROR);
        }
        return Result.info(CodeMsg.PASSWORD_CORRECT);
    }

//    @CacheEvict(cacheNames="user", allEntries = true)
    @ApiOperation(value = "用户退出登录")
    @GetMapping("/doLogout")
    public String doLogout(HttpServletRequest request, HttpServletResponse response) {
        removeCookie(request, response);
        log.warn("退出登录");
        return "redirect:/index";
    }

    /**
     * 退出登录时删除redis session和cookie
     *
     * @return void
     * @Param [request, response]
     */
    private void removeCookie(HttpServletRequest request, HttpServletResponse response) {
        String token = TokenUtil.getCookieToken(request);
        //redis中删除session
        redisUtil.del(TokenUtil.COOKIE_NAME_TOKEN + "::" + token);
        //删除cookie
        Cookie cookie = new Cookie(TokenUtil.COOKIE_NAME_TOKEN, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 用户中心
     *
     * @return java.lang.String
     * @Param [request, response, map]
     */
    @ApiOperation(value = "前往用户信息页")
    @GetMapping("/profile")
    public String userProfile(HttpServletRequest request, Map<String, User> map) {
        String token = TokenUtil.getCookieToken(request);
        if (token.equals("")){
            throw new GlobalException(CodeMsg.USER_NOT_LOGIN);
        }
        User user = userService.getByToken(token);
        map.put("user", user);
        return "user/profile";
    }

    @Value("${file.imagesfolder}")
    String upload;

    @ApiOperation(value = "上传头像")
    @PostMapping("/upload/icon")
    @ResponseBody
    public Result<String> upload(MultipartFile mf, HttpServletRequest request) {
        // 创建图片上传目录
        String uname = request.getParameter("uname");
        File file = new File(upload, uname);
        if (!file.exists()) {
            file.mkdir();
        }
        // 获取要上传图片的文件名
        String fileName = mf.getOriginalFilename();
        log.warn(fileName);
        // 改名
        String newName = System.currentTimeMillis() + fileName;
        log.warn(newName);
        try {
            //上传
            mf.transferTo(new File(file, newName));
            String icon = "/assets/images/user/" + uname + "/" + newName;
            //更新数据库
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("name", uname).set("icon", icon);
            userService.update(updateWrapper);
            //更新redis
            redisUtil.set(TokenUtil.COOKIE_NAME_TOKEN + "::" + TokenUtil.getCookieToken(request)
                    , JSON.toJSONString(userService.findByUsername(uname))
                    , TokenUtil.TOKEN_EXPIRE);
            return Result.success(icon, "");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.info(CodeMsg.FAILED);
        }
    }

//    @CachePut(cacheNames = "user")
    @ApiOperation(value = "更新用户信息")
    @PutMapping("/profile/update")
    @ResponseBody
    public Result<String> update(HttpServletRequest request, HttpServletResponse response,
                                 @RequestBody Map<String, String> map) {
        try {
            String token = TokenUtil.getCookieToken(request);
            Integer id = userService.getByToken(token).getId();
            //更新数据库
            User user = new User();
            user.setId(id);
            String name = map.get("name");
            int age = !map.get("age").equals("") ? Integer.parseInt(map.get("age")) : 0;
            String gender = map.get("gender");
            String phone = map.get("phone");
            user.setName(name);
            user.setAge(age);
            user.setGender(gender);
            user.setPhone(phone);
            userService.updateById(user);
            //更新redis
            redisUtil.set(TokenUtil.COOKIE_NAME_TOKEN + "::" + token
                    , JSON.toJSONString(userService.getById(id))
                    , TokenUtil.TOKEN_EXPIRE);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Result.info(CodeMsg.FAILED);
        }
        return Result.info(CodeMsg.SUCCESS);
    }

    @ApiOperation(value = "更新用户信息时校验网名是否被别人使用")
    @GetMapping("/profile/update/checkName")
    @ResponseBody
    public Result<String> checkName(@ApiParam(value = "username", required = true) @RequestParam("username") String username,
                                    HttpServletRequest request) {
        User user = userService.getByToken(TokenUtil.getCookieToken(request));
        //除当前登录用户以外的其他用户
        if (!user.getName().equals(username) && userService.findByUsername(username) != null)
            return Result.info(CodeMsg.USERNAME_EXISTED);
        return Result.info(CodeMsg.USERNAME_NOT_EXIST);
    }

    @CachePut(cacheNames = "user")
    @ApiOperation(value = "更新密码")
    @PutMapping("/profile/update/pwd")
    @ResponseBody
    public Result<String> updatePwd(HttpServletRequest request, @RequestParam String newPwd){
        if (newPwd.length() < 6 || newPwd.length() > 20){
            return Result.info(CodeMsg.FAILED);
        }
        log.warn(newPwd);
        try {
            String token = TokenUtil.getCookieToken(request);
            User localUser = userService.getByToken(token);
            Integer id = localUser.getId();
            //更新数据库
            User user = new User();
            user.setId(id);
            user.setPwd(newPwd);
            userService.updateById(user);
            //更新redis
            redisUtil.set(TokenUtil.COOKIE_NAME_TOKEN + "::" + token
                    , JSON.toJSONString(userService.getById(id))
                    , TokenUtil.TOKEN_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.info(CodeMsg.SUCCESS);
    }

    @ApiOperation("存储用户留言")
    @PostMapping("/comment")
    @ResponseBody
    public void userComment(@RequestBody Map<String, String> map, HttpServletRequest request){
        String comment = map.get("comment");
        User user = userService.getByToken(TokenUtil.getCookieToken(request));
        Comment comment1 = new Comment();
        comment1.setUserId(user.getId());
        comment1.setCommentTime(new Timestamp(System.currentTimeMillis()));
        comment1.setText(comment);
        commentMapper.save(comment1);
    }
}

