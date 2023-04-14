package com.zhaowei.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhaowei.educms.entity.CrmBanner;
import com.zhaowei.educms.mapper.CrmBannerMapper;
import com.zhaowei.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author zhaowei
 * @since 2023-03-17
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public List<CrmBanner> selectAllBanner() {
        List<CrmBanner> list = baseMapper.selectList(null);
        return list;
    }
}
