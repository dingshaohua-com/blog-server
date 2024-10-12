package com.dshvv.blogserver.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dshvv.blogserver.entity.User;
import com.dshvv.blogserver.service.IArticleService;
import com.dshvv.blogserver.service.ITypeService;
import com.dshvv.blogserver.service.IUserService;
import com.dshvv.blogserver.utils.*;
import com.dshvv.blogserver.vo.ArticleCountVO;
import com.dshvv.blogserver.vo.MasterInfoVO;
import io.swagger.annotations.ApiOperation;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@RequestMapping
@RestController
public class OtherController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ITypeService typeService;

    @Autowired
    private VerifyCodeHelper verifyCodeHelper;

    @Autowired
    private HttpHelper httpHelper;

    // 每次都需要创建，甚是麻烦，提取
    private User getOne(String column, String Val) {
        QueryWrapper queryWrapper = new QueryWrapper<User>().eq(column, Val);
        return userService.getOne(queryWrapper);
    }

    @ApiOperation("起始页")
    @GetMapping("/")
    public ModelAndView doHome() {
        return new ModelAndView("index");
    }

    @ApiOperation("测试")
    @GetMapping("/test")
    public JsonResult doTest() {
        return JsonResult.success();
    }

    // 发送邮件验证码
    @ApiOperation("发送邮件验证码")
    @PostMapping("/sendVerifyCode")
    public JsonResult sendVerifyCode(@RequestBody Map<String, String> map) throws Exception {
        String email = map.get("email");
        verifyCodeHelper.sendVerifyCode(email);
        return JsonResult.success();
    }

    // 注册
    @ApiOperation("注册")
    @PostMapping("/reg")
    public JsonResult<String> register(@RequestBody Map<String, String> map) {
        User userParams = JSONObject.parseObject(JSONObject.toJSONString(map), User.class);
        userParams.setRole("consumer"); // root 超管 普管 凡人  注册的只能是凡人
        if (ObjectUtils.isEmpty(userParams.getAvatar())) { // 没有头像给默认头像
            userParams.setAvatar("https://yh-1257381014.cos.ap-beijing.myqcloud.com/blog/upload/default-avatar.jpg");
        }
        User user = this.getOne("email", userParams.getEmail());
        // 当邮箱已经被注册的时候
        boolean isExist = !ObjectUtils.isEmpty(user) && user.getEmail().equals(userParams.getEmail());
        if (isExist) {
            return JsonResult.failed("账号已存在");
        }
        // 当时新用户注册的时候
        String verifyCode = map.get("verifyCode");
//        boolean effective = verifyCodeHelper.checkVerifyCode(userParams.getEmail(),verifyCode);
//        if (effective) {
        Boolean flag = userService.save(userParams);
        return flag ? JsonResult.success("注册成功") : JsonResult.failed("注册失败");
//        } else {
//            return JsonResult.failed("验证码无效");
//        }
    }

    // 登录
    @ApiOperation("登录")
    @PostMapping("/login")
    public JsonResult<String> doLogin(@RequestParam String email, @RequestParam String pwd) {
        User user = this.getOne("email", email);
        if (ObjectUtils.isEmpty(user)) {
            return JsonResult.failed("用户不存在");
        } else {
            if (user.getPwd().equals(pwd)) {
                StpUtil.login(email);
                return JsonResult.success("登录成功");
            } else {
                return JsonResult.failed("密码输入有误，请重新输入");
            }
        }
    }

    //    // 注册并登录
    @ApiOperation("注册并登录")
    @PostMapping("/reg_login")
    public JsonResult<String> doRegAndLogin(@RequestBody Map<String, String> map) {
        User userParams = JSONObject.parseObject(JSONObject.toJSONString(map), User.class);
        JsonResult regRes = this.register(map);
        if (regRes.getCode() == 0 || regRes.getMsg().equals("账号已存在")) {
            return this.doLogin(userParams.getEmail(), userParams.getPwd());
        } else {
            return regRes;
        }
    }


    // 文件上传
    @ApiOperation("上传文件")
    @PostMapping("/uploadFile")
    public JsonResult<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String filePath = CosHelper.uploadFile(file);
        return JsonResult.success(filePath);
    }

    // 获取当前登录用户信息
    @ApiOperation("获取当前登录用户信息")
    @GetMapping("/userInfo")
    public JsonResult<User> getUserInfo() {
        String email = StpUtil.getLoginIdAsString();
        User user = this.getOne("email", email);
        user.setPwd(null);
        if (ObjectUtils.isEmpty(user)) {
            return JsonResult.failed("用户不存在");
        }
        return JsonResult.success(user);
    }

    // 获取当前登录用户信息
    @ApiOperation("获取主人用户信息")
    @GetMapping("/masterInfo")
    public JsonResult<MasterInfoVO> getMasterInfo() {
        // 用户信息
        User user = this.getOne("email", "869043928@qq.com");
        user.setPwd(null);
        // 文章按照列表类型分组
        List<ArticleCountVO> articleCounts = articleService.getArticleCount();
        // 组合
        MasterInfoVO masterInfoVO = JsonUtils.json2Object(JsonUtils.object2Json(user), MasterInfoVO.class);
        masterInfoVO.setArticleCounts(articleCounts);
        return JsonResult.success(masterInfoVO);
    }


    // 获取当前登录用户信息
    @ApiOperation("设置站长信息")
    @PostMapping("/saveMaster")
    public JsonResult<Boolean> saveMaster(@RequestBody User user) {
        // 用户信息
        user.setId(29);
        boolean result = userService.updateById(user);
        return JsonResult.success(result);
    }

    // 随机获取一张二次元图片
    @ApiOperation("随机获取一张二次元图片")
    @GetMapping(value = {"/img", "/img/{type}"})
    public void getImg(HttpServletResponse response, @PathVariable(value = "type", required = false) String type) throws Exception {
        String imgApiUrl = "https://api.ixiaowai.cn/api/api.php?return=json";

        if (!StringUtils.isBlank(type)) {
            if (type.equals("fj")) {
                imgApiUrl = "https://api.ixiaowai.cn/gqapi/gqapi.php?return=json";
            }
        }

        // 拿到图片的url
        String imgUrl = (String) httpHelper.sendGetReturnJson(imgApiUrl).get("imgurl");
        System.out.println(imgUrl);

        // 根据图片url拿到二进制流 并返回前端
        BufferedInputStream imgStream = httpHelper.sendGetReturnStream(imgUrl);
        OutputStream out = response.getOutputStream();

        // 读取文件流
        int len = 0;
        byte[] buffer = new byte[1024 * 3];
        while ((len = imgStream.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.flush();
        out.close();
    }
}