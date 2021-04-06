package com.example.jqssm.controller;

import com.example.jqssm.config.RabbitMQConfig;
import com.example.jqssm.dao.UserMapper;
import com.example.jqssm.po.Shuo;
import com.example.jqssm.po.User;
import com.example.jqssm.po.num;
import com.example.jqssm.po.pin;
import com.example.jqssm.service.RedisService;
import com.example.jqssm.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/json")
    public User json(@RequestBody User user,HttpSession session) {
        System.out.print("sa");
        int i = userMapper.denru(user.getUsername(),user.getPassword());
        user.setUserid(i);
        User user1 = (User)session.getAttribute("user");
        return user1;
    }
    @RequestMapping("/rabbitmq")
    public Map<String,String>  rabbitmq() {
        Map<String,String> map = new HashMap<>();
        map.put("user","sdh");
        return map;
    }
    @RequestMapping("/redis")
    public User redis(@RequestBody User user,HttpSession session) {
        System.out.print("sa");
        User user1 = userService.denru(user.getUsername(),user.getPassword());
        return user1;
    }
    @RequestMapping("/json1")
    public Map<String,User> json1(@RequestBody User user) {
        System.out.print("ssss");
        int i = userMapper.denru(user.getUsername(),user.getPassword());
        user.setUserid(i);
        Map<String,User> map = new HashMap<>();
        map.put("user",user);
        return map;
    }
    @RequestMapping("/denru")
    public Map<String,String> denru(@RequestBody User user,HttpSession session) {
        System.out.print("denru");
        User user1=userService.denru(user.getUsername(),user.getPassword());
        Map<String,String> map = new HashMap<>();
        if (user1!=null) {
            session.setAttribute("user",user1);
            map.put("zt","1");
        } else {
            map.put("zt","2");
        }
        return map;
    }

    @RequestMapping("/zhuche")
    public Map<String,String> zhuche(@RequestBody User user) {
        System.out.print("zhuche");
        int i=0;
        i = userService.zhuche(user);
        Map<String,String> map = new HashMap<>();
        if (i>0) {
            map.put("zt","1");
        } else {
            map.put("zt","2");
        }
        return map;
    }

    @RequestMapping("/users")
    public List<User> users(@RequestBody User user) {
        System.out.print("/users");
        return userService.users();
    }
    @RequestMapping("/shuo")
    public Map<String,String> shuo(@RequestBody Shuo shuo,HttpSession session) {
        System.out.print("/shuo");
        User user1 = (User)session.getAttribute("user");
        shuo.setUserid(user1.getUserid());
        int i=0;
        i = userService.shuo(shuo);
        Map<String,String> map = new HashMap<>();
        if (i>0) {
            map.put("zt","1");
        } else {
            map.put("zt","2");
        }
       return map;
    }
    @RequestMapping("/shuos")
    public List<Shuo> shuos(@RequestBody Shuo shuo) {
        System.out.print("/shous");
        return userService.shuos();
    }
    @RequestMapping("/shuoss")
    public List<Shuo> shuoss(@RequestBody Shuo shuo,HttpSession session) {
        System.out.print("/shouss");
        User user =(User) session.getAttribute("seeuser") ;
        return userService.shuoss(user.getUserid());
    }

    @RequestMapping("/pin")
    public Map<String,String> pin(@RequestBody pin pin,HttpSession session) {
        System.out.print("/pin");
        User user1 = (User)session.getAttribute("user");
        pin.setUserid(user1.getUserid());
//        pin.setUserid(1);
        int i=0;
        i=userService.pin(pin);
        Map<String,String> map = new HashMap<>();
        if (i>0) {
            map.put("zt","1");
        } else {
            map.put("zt","2");
        }
        return map;
    }

    @RequestMapping("/ppin")
    public List<pin> ppin(@RequestBody pin pin, HttpSession session) {
        List<pin> ppin = null;
        System.out.print("/ppin");
        User user1 = (User)session.getAttribute("user");
        pin.setUserid(user1.getUserid());
        int i=0;
        i=userService.pin(pin);
        if (i>0) {
            ppin =userService.ppin(pin);
        }
        return ppin ;
    }
    @RequestMapping("/dianzhan")
    public Map<String,String> dianzhan(@RequestBody Shuo shuo,HttpSession session) {
        User user1 = (User)session.getAttribute("user");
        shuo.setUserid(user1.getUserid());
//        shuo.setUserid(1);
        int i=0;
        i=userService.dianzhan(shuo);
        Map<String,String> map = new HashMap<>();
        if (i>0) {
            String b=i+"";
            map.put("zt",b);
        } else {
            map.put("zt","-1");
        }
        return map;
    }
    @RequestMapping("/getname")
    public User getname(HttpSession session) {
        User user1 = (User)session.getAttribute("user");
        return user1;
    }
    @RequestMapping("/cha")
    public Map<String,String> cha(@RequestBody User user,HttpSession session) {
        session.setAttribute("seeuser",user);
        Map<String,String> map = new HashMap<>();
        map.put("zt","1");
        return map;
    }
    @RequestMapping("/num")
    public num num(HttpSession session) {
        User user1 = (User)session.getAttribute("user");
        return userService.num(user1.getUserid());
    }







}