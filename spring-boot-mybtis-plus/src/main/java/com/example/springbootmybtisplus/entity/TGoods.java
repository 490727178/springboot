package com.example.springbootmybtisplus.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zengrenshang
 * @since 2020-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TGoods extends Model<TGoods> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 商品名称
     */
    private String goodsName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
