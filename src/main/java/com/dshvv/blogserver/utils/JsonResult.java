package com.dshvv.blogserver.utils;

import org.junit.platform.commons.util.ToStringBuilder;
import java.io.Serializable;
import java.util.Objects;

public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 5598308977637490090L;

    private int code;

    private String msg;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public static JsonResult success() {
        return success(null);
    }

    public static <T> JsonResult success(T data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(0);
        jsonResult.setMsg("SUCCESS");
        if (data != null) {
            jsonResult.setData(data);
        }
        return jsonResult;
    }

    public static JsonResult failed() {
        return failed("FAILED");
    }

    public static JsonResult failed(String msg) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(1);
        jsonResult.setMsg(msg);
        return jsonResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JsonResult)) {
            return false;
        }
        JsonResult result = (JsonResult) o;
        return code == result.code &&
                Objects.equals(msg, result.msg) &&
                Objects.equals(data, result.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, msg, data);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("msg", msg)
                .append("data", data)
                .toString();
    }

}
