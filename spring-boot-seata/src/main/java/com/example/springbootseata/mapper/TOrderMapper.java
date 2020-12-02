package com.example.springbootseata.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.springbootseata.entity.TOrder;
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
public interface TOrderMapper extends BaseMapper<TOrder> {
    //1 新建订单
    @DS("seata_order")
    void create(TOrder order);

    //2 修改订单状态，从零改为1
    @DS("seata_order")
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
