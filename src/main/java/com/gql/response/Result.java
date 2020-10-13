package com.gql.response;

/**
 * @author gql
 * @date 2020/7/30
 **/
public class Result {

    private int code;
    private String msg;
    private Object data;

    public Result() {

    }

    public static Result NEW() {
        return new Result();
    }

    public Result addCode(int code) {
        this.code = code;
        return this;
    }

    public Result addMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result addData(Object data) {
        this.data = data;
        return this;
    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * <p>返回成功状态的结果对象。</p>
     *
     * @param msg 消息内容
     * @return 成功状态的结果对象
     */
    public static Result success(String msg) {
        return new Result(Status.SUCCESS.getValue(), msg, null);
    }

    /**
     * <p>返回成功状态的结果对象。</p>
     *
     * @param msg  消息内容
     * @param data 结果数据
     * @return 成功状态的结果对象
     */
    public static Result success(String msg, Object data) {
        return new Result(Status.SUCCESS.getValue(), msg, data);
    }

    /**
     * <p>返回成功状态的结果对象。</p>
     * <p>消息内容为默认配置，结果数据为空。</p>
     *
     * @param data 结果数据
     * @return 成功状态的结果对象
     */
    public static Result success(Object data) {
        return new Result(Status.SUCCESS.getValue(), "system.result.success", data);
    }

    /**
     * <p>返回成功状态的结果对象。</p>
     * <p>消息内容为默认配置，结果数据为空。</p>
     *
     * @return 成功状态的结果对象
     */
    public static Result success() {
        return new Result(Status.SUCCESS.getValue(), "system.result.success", null);
    }

    /**
     * <p>返回错误状态的结果对象。</p>
     *
     * @param code 消息代码
     * @param msg  消息内容
     * @return 错误状态的结果对象
     */
    public static Result error(int code, String msg) {
        return new Result(code, msg, null);
    }

    /**
     * <p>返回错误状态的结果对象。</p>
     *
     * @param msg 消息内容
     * @return 错误状态的结果对象
     */
    public static Result error(String msg) {
        return new Result(Status.ERROR.getValue(), msg, null);
    }

    /**
     * <p>返回错误状态的结果对象。</p>
     * <p>消息内容为默认配置，结果数据为空。</p>
     *
     * @return 错误状态的结果对象
     */
    public static Result error() {
        return new Result(Status.ERROR.getValue(), "system.result.error", null);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public static enum Status {
        SUCCESS(10000), ERROR(20000);
        private int value;
        Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }
}
