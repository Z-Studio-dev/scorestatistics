package cn.scorestatistics.demo.exception;

public class ScoreException extends RuntimeException {

    private String msg;

    public ScoreException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
