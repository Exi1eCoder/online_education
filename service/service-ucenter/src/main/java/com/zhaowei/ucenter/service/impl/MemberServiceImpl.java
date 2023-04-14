package com.zhaowei.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhaowei.commonutils.JwtUtils;
import com.zhaowei.commonutils.MD5;
import com.zhaowei.commonutils.exceptionhandler.EduException;
import com.zhaowei.ucenter.entity.Member;
import com.zhaowei.ucenter.entity.vo.RegisterVo;
import com.zhaowei.ucenter.mapper.MemberMapper;
import com.zhaowei.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author zhaowei
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //登录的方法
    @Override
    public String login(Member member) {
        //获取登录手机号和密码
        String mobile = member.getMobile();
        String password = member.getPassword();
        //手机号密码非空判断
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new EduException(20001, "登录失败");
        }
        //判断手机号是否正确
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        Member mobileMember = baseMapper.selectOne(wrapper);
        //判断查询对象是否为空
        if (mobileMember == null) {//没有这个手机号
            throw new EduException(20001, "登录失败");
        }
        //判断密码
        //把输入的密码进行加密，再和数据库密码进行比较
        //加密方式MD5
        if (!MD5.encrypt(password).equals(mobileMember.getPassword())) {
            throw new EduException(20001, "密码错误");
        }
        //判断用户是否被禁用
        if (mobileMember.getIsDisabled()) {
            throw new EduException(20001, "用户禁用");
        }
        //登录成功
        return JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
    }

    //注册的方法
    @Override
    public void register(RegisterVo registerVo) {
        //获取住的的数据
        String code = registerVo.getCode();//验证码
        String mobile = registerVo.getMobile();//手机号
        String nickname = registerVo.getNickname();//昵称
        String password = registerVo.getPassword();//密码

        //非空判断
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(mobile)
                || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password)) {
            throw new EduException(20001, "注册失败");
        }
        //判断验证码
        //获取redis验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        //为测试方便设置验证码值为1234
//        String redisCode = "1234";
        if (!code.equals(redisCode)) {
            throw new EduException(20001, "验证码不正确，或已失效");
        }
        //判断手机号是否重复，表里面存在相同的手机号不进行添加
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new EduException(20001, "注册失败");
        }
        //数据添加到数据库中
        Member member = new Member();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);//用户不禁用
        member.setAvatar("http://localhost:6065/avatar/default_avatar.png");
        baseMapper.insert(member);
    }


}
