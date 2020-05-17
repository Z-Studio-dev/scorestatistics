package cn.scorestatistics.demo.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class TbStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    private String nickname;

    private String inclass;

    private int fraction;

    private int fraction_change;

    private String sex;

    private int state;

    private String description;

    private int role_id;

    private String file;

    private Date created;

    private Date updated;

    public TbStudent() { }

    public TbStudent(long id, String username, String password, String nickname, String inclass, int fraction, int fraction_change, String sex, int state, String description, int role_id, String file, Date created, Date updated) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.inclass = inclass;
        this.fraction = fraction;
        this.fraction_change = fraction_change;
        this.sex = sex;
        this.state = state;
        this.description = description;
        this.role_id = role_id;
        this.file = file;
        this.created = created;
        this.updated = updated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getFraction() {
        return fraction;
    }

    public void setFraction(int fraction) {
        this.fraction = fraction;
    }

    public int getFraction_change() {
        return fraction_change;
    }

    public void setFraction_change(int fraction_change) {
        this.fraction_change = fraction_change;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
