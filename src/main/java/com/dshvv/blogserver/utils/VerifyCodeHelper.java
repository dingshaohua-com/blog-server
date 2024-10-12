package com.dshvv.blogserver.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class VerifyCodeHelper {
    @Autowired
    private EmailHelper emailHelper;

    @Autowired
    private RedisHelper redisHelper;

    public void sendVerifyCode(String email){
        String verifyCode = String.valueOf((int)((Math.random()*9+1)*100000));
        redisHelper.set(email,verifyCode, 60);
        emailHelper.sendSimpleMail(email, "您的验证码是："+verifyCode+"。\n请妥善保管，有效期为1分钟。");
    }

    public boolean checkVerifyCode(String email, String verifyCode){
        boolean effective = false;
        if(redisHelper.hasKey(email) && redisHelper.get(email).equals(verifyCode)){
            effective = true;
        }
        return effective;
    }

}
