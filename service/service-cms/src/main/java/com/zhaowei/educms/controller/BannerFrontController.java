package com.zhaowei.educms.controller;

import com.zhaowei.commonutils.ResponseInfo;
import com.zhaowei.educms.entity.CrmBanner;
import com.zhaowei.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 前台banner表前端控制器
 */
@RestController
@RequestMapping("/educms/bannerfront")
//@CrossOrigin
public class BannerFrontController {
    @Autowired
    private CrmBannerService bannerService;

    //查询所有banner
    @Cacheable(value = "banner", key = "'selectIndexList'")
    @GetMapping("getAllBanner")
    public ResponseInfo getAllBanner(){
        List<CrmBanner> list = bannerService.selectAllBanner();
        return ResponseInfo.ok().data("list", list);
    }
}
