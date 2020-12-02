package com.example.springbootseata.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.springbootseata.entity.TStorage;
import com.example.springbootseata.mapper.TStorageMapper;
import com.example.springbootseata.service.TStorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
public class TStorageServiceImpl extends ServiceImpl<TStorageMapper, TStorage> implements TStorageService {

    @Resource
    private TStorageMapper storageMapper;

    /**
     * 扣减库存
     */
    @DS("seata_storage")
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------->storage-service中扣减库存开始");
        storageMapper.decrease(productId,count);
        log.info("------->storage-service中扣减库存结束");
    }
}
