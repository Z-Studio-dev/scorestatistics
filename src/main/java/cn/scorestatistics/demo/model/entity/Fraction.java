package cn.scorestatistics.demo.model.entity;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String classname;

    private Integer fraction;

    private Integer fraction_change;

    public Fraction(){

    }

    public Fraction(Long id,String username,String password,String nickname,String classname,Integer fraction,Integer fraction_change){
        this.id=id;
        this.username=username;
        this.password=password;
        this.nickname=nickname;
        this.classname=classname;
        this.fraction=fraction;
        this.fraction_change=fraction_change;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getNickname() { return nickname; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getClassname() { return classname; }

    public void setClassname(String classname) { this.classname = classname; }

    public Integer getFraction() { return fraction; }

    public void setFraction(Integer fraction) { this.fraction = fraction; }

    public Integer getFraction_change() { return fraction_change; }

    public void setFraction_change(Integer fraction_change) { this.fraction_change = fraction_change; }
}
