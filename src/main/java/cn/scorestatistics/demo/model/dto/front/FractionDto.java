package cn.scorestatistics.demo.model.dto.front;

import java.io.Serializable;

public class FractionDto implements Serializable {
    private Long id;

    private Integer num;

    private String username;

    private String classname;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Integer getNum() { return num; }

    public void setNum(Integer num) { this.num = num; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getClassname() { return classname; }

    public void setClassname(String classname) { this.classname = classname; }
}
