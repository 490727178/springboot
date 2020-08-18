package com.example.springbootmybtisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmybtisplus.annotation.NeedSetFeildValue;
import com.example.springbootmybtisplus.entity.TOrder;
import com.example.springbootmybtisplus.mapper.TOrderMapper;
import com.example.springbootmybtisplus.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengrenshang
 * @since 2020-04-24
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private TOrderMapper orderMapper;



    @Override
    @NeedSetFeildValue
    public Page<TOrder> pageQuery( int pageNum, int pageSize) {

        Page page = new Page(pageNum,pageSize);
        Page<TOrder> tOrderPage = orderMapper.selectPage(page, new QueryWrapper<>());

        // 需要获得订单的客户姓名
        return tOrderPage;
    }
}
