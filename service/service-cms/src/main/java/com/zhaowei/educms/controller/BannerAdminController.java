package com.zhaowei.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaowei.commonutils.ResponseInfo;
import com.zhaowei.educms.entity.CrmBanner;
import com.zhaowei.educms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author zhaowei
 */
@RestController
@RequestMapping("/educms/banner")
//@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService crmBannerService;

    //1 分頁查詢Banner
    @GetMapping("pageBanner/{page}/{limit}")
    public ResponseInfo pageBanner(@PathVariable long page,@PathVariable long limit){
        Page<CrmBanner> pageBanner = new Page<>(page, limit);
        crmBannerService.page(pageBanner,null);
        return ResponseInfo.ok().data("items", pageBanner.getTotal());
    }

    //2 添加banner
    @PostMapping("addBanner")
    public ResponseInfo addBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.save(crmBanner);
        return ResponseInfo.ok();
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public ResponseInfo get(@PathVariable String id){
        CrmBanner crmBanner = crmBannerService.getById(id);
        return ResponseInfo.ok().data("item", crmBanner);
    }

    @ApiOperation(value = "修改Banner")
    //修改
    @PutMapping("update")
    public ResponseInfo updateById(@RequestBody CrmBanner crmBanner){
        crmBannerService.updateById(crmBanner);
        return ResponseInfo.ok();
    }

    //刪除
    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public ResponseInfo addBanner(@PathVariable String id){
        crmBannerService.removeById(id);
        return ResponseInfo.ok();
    }
}

