package com.example.jqssm.po;

import java.util.List;

public class Shuo {
    private int shuoid;
    private String pinlun;
    private String file;
    private String time;
    private String zt;
    private String shuoname;
    private int userid;
    private List<pin> pins;
    private int zhannum;

    public int getShuoid() {
        return shuoid;
    }

    public void setShuoid(int shuoid) {
        this.shuoid = shuoid;
    }

    public String getPinlun() {
        return pinlun;
    }

    public void setPinlun(String pinlun) {
        this.pinlun = pinlun;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public List<pin> getPins() {
        return pins;
    }

    public void setPins(List<pin> pins) {
        this.pins = pins;
    }

    public int getZhannum() {
        return zhannum;
    }

    public void setZhannum(int zhannum) {
        this.zhannum = zhannum;
    }

    public String getShuoname() {
        return shuoname;
    }

    public void setShuoname(String shuoname) {
        this.shuoname = shuoname;
    }

    @Override
    public String toString() {
        return "Shuo{" +
                "shuoid=" + shuoid +
                ", pinlun='" + pinlun + '\'' +
                ", file='" + file + '\'' +
                ", time='" + time + '\'' +
                ", zt='" + zt + '\'' +
                ", userid=" + userid +
                ", pins=" + pins +
                ", zhannum=" + zhannum +
                '}';
    }
}
