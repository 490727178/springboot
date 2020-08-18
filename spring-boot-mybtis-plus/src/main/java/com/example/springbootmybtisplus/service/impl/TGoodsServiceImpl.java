package com.example.springbootmybtisplus.service.impl;

import com.example.springbootmybtisplus.entity.TGoods;
import com.example.springbootmybtisplus.mapper.TGoodsMapper;
import com.example.springbootmybtisplus.service.TGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengrenshang
 * @since 2020-04-27
 */
@Service
public class TGoodsServiceImpl extends ServiceImpl<TGoodsMapper, TGoods> implements TGoodsService {

    @Autowired
    private TGoodsMapper goodsMapper;

    @Override
    public TGoods findById(String goodsId) {
        return goodsMapper.selectById(goodsId);
    }
}
