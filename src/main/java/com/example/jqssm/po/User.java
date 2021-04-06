package com.example.jqssm.po;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private int userid;
    private String username;
    private String password;
    private String name;
    private String tupian;
    private List<Shuo> shuos;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTupian() {
        return tupian;
    }

    public void setTupian(String tupian) {
        this.tupian = tupian;
    }

    public List<Shuo> getShuos() {
        return shuos;
    }

    public void setShuos(List<Shuo> shuos) {
        this.shuos = shuos;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", tupian='" + tupian + '\'' +
                '}';
    }
}
