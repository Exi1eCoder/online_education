package com.zhaowei.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhaowei.commonutils.ResponseInfo;
import com.zhaowei.eduservice.entity.EduCourse;
import com.zhaowei.eduservice.entity.EduTeacher;
import com.zhaowei.eduservice.service.EduCourseService;
import com.zhaowei.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/indexfront")
public class IndexFrontController {
    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;
    //查询前8条热门课程，查询前4条名师
    @Cacheable(value = "ctlist", key = "'selectCtList'")
    @GetMapping("index")
    public ResponseInfo index(){
        QueryWrapper<EduCourse> wrapperCourse = new QueryWrapper<>();
        wrapperCourse.orderByDesc("id").last("limit 8");
        List<EduCourse> courseList = courseService.list(wrapperCourse);

        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id").last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);
        return ResponseInfo.ok().data("courseList", courseList).data("teacherList", teacherList);
    }
}
