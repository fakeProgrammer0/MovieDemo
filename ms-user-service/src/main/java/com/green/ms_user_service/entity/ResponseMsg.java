package com.green.ms_user_service.entity;

/**
 * @author: fakeProgrammer
 * @createdDate: 2018/10/10
 * @description:
 */
public class ResponseMsg
{
    private int status;
    private String msg;
    private Object data;
    
    public ResponseMsg()
    {
    }
    
    public ResponseMsg(int status, String msg)
    {
        this.status = status;
        this.msg = msg;
    }
    
    // 为了方便地统一消息格式，临时定义的静态方法
    // 正确地做法：改为工厂模式
    public static ResponseMsg okResponse()
    {
        return new ResponseMsg(200, "ok");
    }
    
    public static ResponseMsg errorResponse()
    {
        return new ResponseMsg(400, "bad request");
    }
    
    public static ResponseMsg notFoundResponse()
    {
        return new ResponseMsg(404, "Not Found");
    }
    
    // 可以加个注解，不作为JSON格式化
    @Override
    public String toString()
    {
        return "{" +
                "status:" + this.status + ","
                + "msg:\'" + this.msg + "\',"
                + "data:" + this.data
                + "}";
    }
    
    public int getStatus()
    {
        return status;
    }
    
    public void setStatus(int status)
    {
        this.status = status;
    }
    
    public String getMsg()
    {
        return msg;
    }
    
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    
    public Object getData()
    {
        return data;
    }
    
    public void setData(Object data)
    {
        this.data = data;
    }
}
