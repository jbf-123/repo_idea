package com.lagou.dao;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {

    //查询所有广告位
    public List<PromotionSpace> findAllPromotionSpace();
    //新增广告位
    public void savePromotionSpace(PromotionSpace promotionSpace);
    //回显广告位信息
    public List<PromotionSpace> findPromotionSpaceById(Integer id);
    //修改广告位名称
    public void updatePromotion(PromotionSpace promotionSpace);
}
