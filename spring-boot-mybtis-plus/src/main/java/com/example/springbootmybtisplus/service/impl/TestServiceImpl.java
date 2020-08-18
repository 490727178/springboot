package com.example.springbootmybtisplus.service.impl;

import com.example.springbootmybtisplus.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @Author: zengrenshang
 * @Date: 2020/5/30 22:51
 */
@Service
public class TestServiceImpl  implements TestService {
    @Override
    public String getStr(String name) {
        return name;
    }
}
