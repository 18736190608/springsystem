package com.gql.exception;

/**
 * @author gql
 * @date 2020/8/9
 **/
public class IORuntiomException extends RuntimeException {
    public IORuntiomException(Throwable e) {
        super(e);
    }

    public IORuntiomException(String message) {
        super(message);
    }



    public IORuntiomException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public IORuntiomException(Throwable throwable, String messageTemplate, Object... params) {
        super(messageTemplate,  throwable);
    }

    /**
     * 导致这个异常的异常是否是指定类型的异常
     *
     * @param clazz 异常类
     * @return 是否为指定类型异常
     */
    public boolean causeInstanceOf(Class<? extends Throwable> clazz) {
        Throwable cause = this.getCause();
        if (null != cause && clazz.isInstance(cause)) {
            return true;
        }
        return false;
    }
}
