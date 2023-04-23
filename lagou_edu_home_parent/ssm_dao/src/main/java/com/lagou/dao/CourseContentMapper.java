package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {

    public List<CourseSection> findSectionAndLessonByCourseId(Integer id);
    //回显章节对应的课程信息
    public Course findCourseByCourseId(int CourseId);
    //新增章节信息
    public void saveSection(CourseSection section);
    //修改章节信息
    public void updateSection(CourseSection section);
    //修改章节状态
    public void updateSectionStatus(CourseSection section);
}
