package cn.scorestatistics.demo.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class TbFractionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String username;

    private String nickname;

    private String inclass;

    private String fraction;

    private String user;

    private String ip;

    private String ip_info;

    private Integer time;

    private Date create_date;

    public TbFractionLog() {
    }

    public TbFractionLog(long id, String name, String username, String nickname, String inclass, String fraction, String user, String ip, String ip_info, Integer time, Date create_date) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.nickname = nickname;
        this.inclass = inclass;
        this.fraction = fraction;
        this.user = user;
        this.ip = ip;
        this.ip_info = ip_info;
        this.time = time;
        this.create_date = create_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getInclass() {
        return inclass;
    }

    public void setInclass(String inclass) {
        this.inclass = inclass;
    }

    public String getFraction() {
        return fraction;
    }

    public void setFraction(String fraction) {
        this.fraction = fraction;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp_info() {
        return ip_info;
    }

    public void setIp_info(String ip_info) {
        this.ip_info = ip_info;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Date getCreateDate() {
        return create_date;
    }

    public void setCreateDate(Date create_date) {
        this.create_date = create_date;
    }
}
