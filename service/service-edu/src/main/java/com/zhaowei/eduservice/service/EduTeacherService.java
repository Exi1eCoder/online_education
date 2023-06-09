package com.zhaowei.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaowei.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author zhaowei
 * @since 2023-03-20
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
