package com.example.jqssm.dao;
import com.example.jqssm.po.Shuo;
import com.example.jqssm.po.User;
import com.example.jqssm.po.pin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    public Integer denru(@Param("username") String username, @Param("password")String password);
    public User denrun(@Param("username") String username, @Param("password")String password);
    public Integer zhuche(User user);
    public List<User> users();
    public Integer shuo(Shuo shuo);
    public List<Shuo> shuos();
    public List<Shuo> shuoss(@Param("userid")Integer userid);
    public Integer zhannum( Shuo shuo);
    public Integer pin(pin pin);
    public List<pin> ppin(pin pin);
    public String name(int userid);
    public Integer dianzhan(Shuo shuo);
    public Integer ifdianzhan(Shuo shuo);
    public Integer addzhan(Shuo shuo);
    public Integer sa(@Param("userid")Integer userid);
    public Integer sb(@Param("userid")Integer userid);
    public Integer sc(@Param("userid")Integer userid);

}
