package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;
    //实现广告分页查询
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage( PromotionAdVO promotionAdVO){
        PageInfo<PromotionAd> allPromotionAdByPage = promotionAdService.findAllPromotionAdByPage(promotionAdVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "分页查询完成", allPromotionAdByPage);
        return responseResult;
    }

    //图片上传
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
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

    //动态修改广告上下线状态
    @RequestMapping("/updatePromotionStatus")
    public ResponseResult updatePromotionStatus(Integer id,Integer status){
        promotionAdService.updatePromotionStatus(id,status);
        return new ResponseResult(true,200,"动态修改状态成功",null);
    }

    //新增和修改广告信息
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){
        if (promotionAd.getId()==null){
            promotionAdService.savePromotionAd(promotionAd);
            return  new ResponseResult(true,200,"新增广告成功",null);
        }else {
            promotionAdService.updatePromotionAd(promotionAd);
            return  new ResponseResult(true,200,"修改广告成功",null);
        }
    }
    //根据id回显广告信息
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(@RequestParam Integer id){
        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
        return  new ResponseResult(true,200,"响应成功",promotionAd);
    }

}
