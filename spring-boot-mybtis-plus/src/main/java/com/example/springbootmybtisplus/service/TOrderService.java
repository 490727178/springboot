package com.example.springbootmybtisplus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmybtisplus.annotation.NeedSetFeildValue;
import com.example.springbootmybtisplus.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengrenshang
 * @since 2020-04-24
 */
public interface TOrderService extends IService<TOrder> {

   @NeedSetFeildValue
   Page<TOrder> pageQuery(int pageNum, int pageSize);
}
