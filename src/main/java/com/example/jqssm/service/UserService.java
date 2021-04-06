package com.example.jqssm.service;

import com.example.jqssm.po.Shuo;
import com.example.jqssm.po.User;
import com.example.jqssm.po.num;
import com.example.jqssm.po.pin;

import java.util.List;

public interface UserService {
    public User denru(String username,String password);
    public Integer zhuche(User user);
    public List<User> users();
    public Integer shuo(Shuo shuo);
    public List<Shuo> shuos();
    public List<Shuo> shuoss( Integer userid);
    public Integer pin(pin pin);
    public List<pin> ppin(pin pin);
    public Integer dianzhan(Shuo shuo);
    public num num(Integer userid);

}
