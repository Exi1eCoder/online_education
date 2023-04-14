package com.zhaowei.ucenter.controller;


import com.zhaowei.commonutils.JwtUtils;
import com.zhaowei.commonutils.ResponseInfo;
import com.zhaowei.ucenter.entity.Member;
import com.zhaowei.ucenter.entity.vo.RegisterVo;
import com.zhaowei.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author zhaowei
 */
@RestController
@RequestMapping("/ucenter/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    //登录
    @PostMapping("login")
    public ResponseInfo loginUser(@RequestBody Member member){
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token = memberService.login(member);
        return ResponseInfo.ok().data("token", token);
    }

    //注册
    @PostMapping("register")
    public ResponseInfo registerUser(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return ResponseInfo.ok();
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public ResponseInfo getMemberInfo(HttpServletRequest request){
        //调用jwt工具类的方法，根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        Member member = memberService.getById(memberId);
        return ResponseInfo.ok().data("userInfo", member);
    }

}

