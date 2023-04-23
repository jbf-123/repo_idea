package com.lagou.service.impl;

import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.domain.PromotionSpace;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        List<PromotionSpace> promotionSpaceList = promotionSpaceMapper.findAllPromotionSpace();
        return promotionSpaceList;
    }

    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {
        Date date = new Date();
        promotionSpace.setSpaceKey("666");
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);
        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    @Override
    public List<PromotionSpace> findPromotionSpaceById(Integer id) {
        PromotionSpace promotionSpace = new PromotionSpace();
        promotionSpace.setUpdateTime(new Date());
        List<PromotionSpace> promotionSpaceList = promotionSpaceMapper.findPromotionSpaceById(id);
        return promotionSpaceList;
    }

    @Override
    public void updatePromotion(PromotionSpace promotionSpace) {
        promotionSpace.setUpdateTime(new Date());
        promotionSpaceMapper.updatePromotion(promotionSpace);
    }
}
