package com.example.springbootseata.service;

import com.example.springbootseata.entity.TStorage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengrenshang
 * @since 2020-12-02
 */
public interface TStorageService extends IService<TStorage> {

    void decrease(Long productId, Integer count);
}
