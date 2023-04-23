package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {
        List<Course> courseList = courseMapper.findCourseByCondition(courseVO);

        return courseList;
    }

    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        //封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(course,courseVO);
        //补全课程信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        //保存课程
        courseMapper.saveCourse(course);
        //获取插入数据的id值
        int id=course.getId();
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(id);
        courseMapper.saveTeacher(teacher);
    }

    @Override
    public CourseVO findCourseById(Integer id) {
        CourseVO courseVO = courseMapper.findCourseById(id);
        return courseVO;
    }

    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        Course course = new Course();
        //补全信息
        Date date = new Date();
        course.setUpdateTime(date);
        BeanUtils.copyProperties(course,courseVO);
        courseMapper.updateCourse(course);

        int id=course.getId();
        Teacher teacher = new Teacher();
        teacher.setCourseId(id);
        teacher.setUpdateTime(date);
        BeanUtils.copyProperties(teacher,courseVO);
        courseMapper.updateTeacher(teacher);


    }

    @Override
    public void updateCourseStatus(int id, int status) {
        Course course = new Course();
        Date date = new Date();
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(date);
        courseMapper.updateCourseStatus(course);
    }


}
