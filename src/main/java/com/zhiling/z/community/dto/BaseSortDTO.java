package com.zhiling.z.community.dto;

import lombok.Data;

import java.util.List;

/**
 *  排序
 * @Author zlhl
 * @Date 2019/12/27
 * @Version V1.0
 **/
@Data
public abstract class BaseSortDTO {

    /**
     * 列名，由前到后进行排序
     */
    List<String> columns;

}
