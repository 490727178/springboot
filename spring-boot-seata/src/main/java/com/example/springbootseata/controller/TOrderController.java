package com.example.springbootseata.controller;


import com.example.springbootseata.constant.CommonResult;
import com.example.springbootseata.entity.TOrder;
import com.example.springbootseata.service.TOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zengrenshang
 * @since 2020-12-02
 */
@RestController
@RequestMapping("/t-order")
public class TOrderController {
    @Resource
    private TOrderService orderService;


    @GetMapping("/order/create")
    public CommonResult create(TOrder order)
    {
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}
