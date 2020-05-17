package cn.scorestatistics.demo.model.dto.front;

public class StudentDto {

    private String username;

    private String password;

    private String nickname;

    private String inclass;

    private int fraction;

    private int fraction_change;

    private String sex;

    private String file;

    private String description;

    private int state;

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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
