package cn.scorestatistics.demo.model.dto.front;

import java.io.Serializable;

public class UserLoginRegist implements Serializable {

    private String userName;

    private String userPwd;

    private String statusKey;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getStatusKey() {
        return statusKey;
    }

    public void setStatusKey(String statusKey) {
        this.statusKey = statusKey;
    }

}
