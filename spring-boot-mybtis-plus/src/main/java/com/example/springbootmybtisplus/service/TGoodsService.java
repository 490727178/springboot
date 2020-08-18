package com.example.springbootmybtisplus.service;

import com.example.springbootmybtisplus.entity.TGoods;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengrenshang
 * @since 2020-04-27
 */
public interface TGoodsService extends IService<TGoods> {


    TGoods findById(String goodsId);
}
