package com.zhaowei.ucenter.service;

import com.zhaowei.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhaowei.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author zhaowei
 */
public interface MemberService extends IService<Member> {

    String login(Member member);

    void register(RegisterVo registerVo);
}
