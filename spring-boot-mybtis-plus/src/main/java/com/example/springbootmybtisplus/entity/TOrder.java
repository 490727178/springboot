package com.example.springbootmybtisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

import com.example.springbootmybtisplus.annotation.NeedSetValue;
import com.example.springbootmybtisplus.service.TGoodsService;
import com.example.springbootmybtisplus.service.TUserService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zengrenshang
 * @since 2020-04-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TOrder extends Model<TOrder> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String userId;

    private String goodsId;


    @TableField(exist=false)
    @NeedSetValue(beanClass = TUserService.class,param = "userId",method = "findById",targetFiled = "name")
    private String userName;


    @TableField(exist=false)
    @NeedSetValue(beanClass = TGoodsService.class,param = "goodsId",method = "findById",targetFiled = "goodsName")
    private String goodsName;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
