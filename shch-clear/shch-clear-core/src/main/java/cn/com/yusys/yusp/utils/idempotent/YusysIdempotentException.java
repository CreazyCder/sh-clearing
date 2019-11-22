package cn.com.yusys.yusp.utils.idempotent;

public class YusysIdempotentException extends RuntimeException {
    //无参构造方法
    public YusysIdempotentException(){

        super();
    }

    //有参的构造方法
    public YusysIdempotentException(String message){
        super(message);

    }

    // 用指定的详细信息和原因构造一个新的异常
    public YusysIdempotentException(String message, Throwable cause){

        super(message,cause);
    }

    //用指定原因构造一个新的异常
    public YusysIdempotentException(Throwable cause) {

        super(cause);
    }
}
