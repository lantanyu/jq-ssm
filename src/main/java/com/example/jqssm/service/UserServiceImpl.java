package com.example.jqssm.service;

import com.example.jqssm.dao.UserMapper;
import com.example.jqssm.po.Shuo;
import com.example.jqssm.po.User;
import com.example.jqssm.po.num;
import com.example.jqssm.po.pin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable(value = "UserCahe",key = "'User.denru'")
    public User denru(String username, String password) {
        System.out.println("yu");
        return userMapper.denrun(username,password);
    }

    @Override
    public Integer zhuche(User user) {
        return userMapper.zhuche(user);
    }

    @Override
    public List<User> users() {
        return userMapper.users();
    }

    @Override
    public Integer shuo(Shuo shuo) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = df.format(date);
        shuo.setTime(str);
        return userMapper.shuo(shuo);
    }

    @Override
    public List<Shuo> shuos() {
        List<Shuo> shuos = userMapper.shuos();
        for (int i=0;i<shuos.size();i++ ) {
            shuos.get(i).setZhannum(userMapper.zhannum(shuos.get(i)));
            shuos.get(i).setShuoname(userMapper.name(shuos.get(i).getUserid()));
            List<pin>pins = shuos.get(i).getPins();
            for (int j=0 ;j<pins.size();j++) {
                pins.get(j).setPinname(userMapper.name(pins.get(j).getUserid()));
            }
            shuos.get(i).setPins(pins);
        }
        return shuos;
    }

    @Override
    public List<Shuo> shuoss(Integer userid) {
        List<Shuo> shuos = userMapper.shuoss(userid);
        for (int i=0;i<shuos.size();i++ ) {
            shuos.get(i).setZhannum(userMapper.zhannum(shuos.get(i)));
            shuos.get(i).setShuoname(userMapper.name(shuos.get(i).getUserid()));
            List<pin>pins = shuos.get(i).getPins();
            for (int j=0 ;j<pins.size();j++) {
                pins.get(j).setPinname(userMapper.name(pins.get(j).getUserid()));
            }
            shuos.get(i).setPins(pins);
        }
        return shuos;
    }

    @Override
    public Integer pin(pin pin) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = df.format(date);
        pin.setPintime(str);
        return userMapper.pin(pin);
    }

    @Override
    public List<pin> ppin(pin pin) {
        List<pin> ppin = userMapper.ppin(pin);
        for (int j=0 ;j<ppin.size();j++) {
            ppin.get(j).setPinname(userMapper.name(ppin.get(j).getUserid()));
        }
        return ppin;
    }

    @Override
    public Integer dianzhan(Shuo shuo) {
        if(userMapper.ifdianzhan(shuo)>0) {
            return -1;
        }else {
            if(userMapper.addzhan(shuo)>0) {
                return userMapper.dianzhan(shuo);
            }
            else {
                return 0;
            }
        }
    }

    @Override
    public num num(Integer userid) {
        num num = new num();
        num.setShuonum(userMapper.sa(userid));
        num.setPinnum(userMapper.sb(userid));
        num.setZhannum(userMapper.sc(userid));
        return num;
    }
}
