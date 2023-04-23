package com.lagou.service;

import com.lagou.domain.PromotionSpace;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PromotionSpaceService {

    public List<PromotionSpace> findAllPromotionSpace();

    public void savePromotionSpace(PromotionSpace promotionSpace);

    public List<PromotionSpace> findPromotionSpaceById(Integer id);

    public void updatePromotion(PromotionSpace promotionSpace);
}
