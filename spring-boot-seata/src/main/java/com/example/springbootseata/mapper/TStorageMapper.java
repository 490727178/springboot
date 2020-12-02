package com.example.springbootseata.mapper;

import com.example.springbootseata.entity.TStorage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zengrenshang
 * @since 2020-12-02
 */
public interface TStorageMapper extends BaseMapper<TStorage> {

    //扣减库存
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
