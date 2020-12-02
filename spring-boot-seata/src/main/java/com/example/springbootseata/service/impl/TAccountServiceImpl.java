package com.example.springbootseata.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.springbootseata.entity.TAccount;
import com.example.springbootseata.mapper.TAccountMapper;
import com.example.springbootseata.service.TAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengrenshang
 * @since 2020-12-02
 */
@Service
@Slf4j
public class TAccountServiceImpl extends ServiceImpl<TAccountMapper, TAccount> implements TAccountService {


    @Resource
    TAccountMapper accountMapper;
    /**
     * 扣减账户余额
     */
    @DS("seata_account")
    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("------->account-service中扣减账户余额开始");
        //模拟超时异常，全局事务回滚
        //暂停几秒钟线程

//        try { TimeUnit.SECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
        accountMapper.decrease(userId,money);
        log.info("------->account-service中扣减账户余额结束");
    }
}
