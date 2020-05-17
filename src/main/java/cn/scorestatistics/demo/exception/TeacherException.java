package cn.scorestatistics.demo.exception;

public class TeacherException extends RuntimeException{


        private String msg;

        public TeacherException(String msg) {
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
