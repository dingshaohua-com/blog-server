package com.dshvv.blogserver.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.dshvv.blogserver.utils.JsonResult;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

//ExceptionHandler的处理顺序是由异常匹配度来决定的，且我们也无法通过其他途径指定顺序(其实也没有必要)

/**
 * 全局异常处理器
 */
@ControllerAdvice // @ControllerAdvice将当前类标识为异常处理的组件
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(HttpStatus.OK)
    protected JsonResult handleException(HttpServletRequest request, NotLoginException ex) {
        JsonResult result = new JsonResult();
        result.setCode(401);
        result.setMsg("用户未登录或已过期，请重新登录!");
        return result;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult handleException(HttpServletRequest req, DuplicateKeyException ex) {
        JsonResult result = new JsonResult();
        result.setCode(1);
        result.setMsg("邮箱已经被注册");
        return result;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult handleException(HttpServletRequest req, HttpMessageNotReadableException ex) {
        JsonResult result = new JsonResult();
        result.setCode(1);
        result.setMsg("body缺少参数");
        return result;
    }

    @ExceptionHandler(Exception.class)  // @ExceptionHandler用于设置所标识方法处理的异常 参数代表异常类型
    @ResponseStatus(HttpStatus.OK)
    public JsonResult handleException(HttpServletRequest req, Exception ex) {
        ex.printStackTrace();
        JsonResult result = new JsonResult();
        result.setCode(1);
        result.setMsg("系统错误："+ex.toString());
        return result;
    }
}