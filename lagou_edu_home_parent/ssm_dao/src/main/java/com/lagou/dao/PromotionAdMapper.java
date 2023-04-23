package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    //分页查询广告信息
    public List<PromotionAd> findAllPromotionAdByPage();

    //修改广告动态上下线状态
    public void updatePromotionStatus(PromotionAd promotionAd);

    //新建广告信息
    public void savePromotionAd(PromotionAd promotionAd);

    //修改广告信息
    public void updatePromotionAd(PromotionAd promotionAd);
    //回显广告信息(根据id查询广告信息)
    public PromotionAd findPromotionAdById(Integer id);

}
