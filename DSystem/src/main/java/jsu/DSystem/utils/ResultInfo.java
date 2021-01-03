package jsu.DSystem.utils;

public class ResultInfo<T> {

    private Integer code;//状态码 1=成功,0=失败
    private String message;//状态信息
    private T result;//响应给前台的对象,javabean,list,map等


    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getResult() {
        return result;
    }
    public void setResult(T result) {
        this.result = result;
    }
}