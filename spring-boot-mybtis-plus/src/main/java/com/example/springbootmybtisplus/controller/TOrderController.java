package com.example.springbootmybtisplus.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmybtisplus.entity.TOrder;
import com.example.springbootmybtisplus.service.TOrderService;
import com.example.springbootmybtisplus.service.impl.TOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zengrenshang
 * @since 2020-04-24
 */
@RestController
@RequestMapping("t-order")
public class TOrderController {

    @Autowired
    private TOrderService orderService;


    @GetMapping("pageQuery")
    public Page<TOrder> pageQuery(){
       return orderService.pageQuery(0,2);
    }

}
