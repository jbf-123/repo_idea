package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){
        List<Course> list = courseService.findCourseByCondition(courseVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);
        return responseResult;
    }

    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        try {
        //1.判断文件是否为空
            if(file.isEmpty()){
                throw new RuntimeException();
            }
        //2.获取项目部署路径
        // D:\apache-tomcat-8.5.56\webapps\ssm_web\
            String realPath = request.getServletContext().getRealPath("/");
        // D:\apache-tomcat-8.5.56\webapps\
            String webappsPath =
                    realPath.substring(0,realPath.indexOf("ssm_web"));
            //3.获取原文件名
            String fileName = file.getOriginalFilename();
        //4.新文件名
            String newFileName = System.currentTimeMillis() +
                    fileName.substring(fileName.lastIndexOf("."));
        //5.上传文件
            String uploadPath = webappsPath+"upload\\";
            File filePath = new File(uploadPath,newFileName);
        //如果目录不存在就创建目录
            if(!filePath.getParentFile().exists()){
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录: " + filePath);
            }
            file.transferTo(filePath);
        //6.将文件名和文件路径返回
            Map<String,String> map = new HashMap<>();
            map.put("fileName",newFileName);
            map.put("filePath","http://localhost:8080/upload/"+newFileName);
            ResponseResult result = new ResponseResult(true,200,"响应成功",map);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //新增或修改 课程信息及讲师的信息,
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        if (courseVO.getId()==null){
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增成功", null);
            return responseResult;
        }else {
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }

    }
    //回显课程信息(根据id查询对应的课程信息及关联的讲师信息)
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){

        CourseVO courseVO = courseService.findCourseById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", courseVO);
        return responseResult;
    }
    //修改课程状态信息
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id,Integer status){
        courseService.updateCourseStatus(id,status);
        HashMap<String, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",map);
        return responseResult;
    }

}
