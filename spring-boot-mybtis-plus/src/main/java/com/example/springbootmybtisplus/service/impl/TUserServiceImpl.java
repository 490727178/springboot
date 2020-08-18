package com.example.springbootmybtisplus.service.impl;

import com.example.springbootmybtisplus.entity.TUser;
import com.example.springbootmybtisplus.mapper.TUserMapper;
import com.example.springbootmybtisplus.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zengrenshang
 * @since 2020-04-24
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {

    @Autowired
    private TUserMapper userMapper;

    @Override
    public TUser findById(String id) {
       return userMapper.selectById(id);
    }
}
