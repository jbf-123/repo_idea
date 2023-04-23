package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

import java.util.List;

public interface PromotionAdService {
    //分页查询广告信息
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO);
    //动态修改广告上下线状态
    public void updatePromotionStatus(Integer id,Integer status);
    //新建广告信息
    public void savePromotionAd(PromotionAd promotionAd);
    //修改广告信息
    public void updatePromotionAd(PromotionAd promotionAd);
    //根据id回显广告信息
    public PromotionAd findPromotionAdById(Integer id);

}
