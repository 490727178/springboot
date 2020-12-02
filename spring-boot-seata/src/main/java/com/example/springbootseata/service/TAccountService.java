package com.example.springbootseata.service;

import com.example.springbootseata.entity.TAccount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengrenshang
 * @since 2020-12-02
 */
public interface TAccountService extends IService<TAccount> {

    void decrease(Long userId, BigDecimal money);
}
