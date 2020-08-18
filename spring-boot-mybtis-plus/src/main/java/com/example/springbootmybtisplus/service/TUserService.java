package com.example.springbootmybtisplus.service;

import com.example.springbootmybtisplus.entity.TUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengrenshang
 * @since 2020-04-24
 */
public interface TUserService extends IService<TUser> {

        public TUser findById(String id);

}
