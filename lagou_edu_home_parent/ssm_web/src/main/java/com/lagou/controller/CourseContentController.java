package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer CourseId){
        List<CourseSection> courseSections = courseContentService.findSectionAndLessonByCourseId(CourseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", courseSections);
        return responseResult;
    }
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer CourseId){
        Course course = courseContentService.findCourseByCourseId(CourseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", course);
        return responseResult;
    }
    @RequestMapping("/saveSection")
    public ResponseResult saveSection(@RequestBody CourseSection section){
        if (section.getId()==null){
            courseContentService.saveSection(section);
            return new ResponseResult(true,200,"新增成功",null);
        }else {
            courseContentService.updateSection(section);
            return new ResponseResult(true,200,"修改成功",null);
        }

    }

    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id,int status){
        courseContentService.updateSectionStatus(id,status);
        ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
        return  responseResult;
    }

}
