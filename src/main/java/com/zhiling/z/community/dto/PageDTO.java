package com.zhiling.z.community.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *  分页模型
 * @Author zlhl
 * @Date 2019/12/24
 * @Version V1.0
 **/
@Data
@Component
public class PageDTO {
    /**
     *  分页当前页
     */
    private Integer pageIndex;
    /**
     *  分页每页行数
     */
    private Integer pageSize;
    /**
     *  分页总页面数
     */
    private Integer pageCount;
    /**
     *  总记录数
     */
    private Integer counts;

    /**
     *  是否显示前一页按钮
     */
    private Boolean showPrevious;
    /**
     *  是否显示第一页按钮
     */
    private Boolean showFirstPage;
    /**
     *  是否显示下一页
     */
    private Boolean showNext;
    /**
     *  是否显示最后一页
     */
    private Boolean showEndPage;
    /**
     *  显示的页面数
     */
    private List<Integer> pages;

    /**
     *  获取页面偏移量
     * @return 偏移量
     */
    public Integer getIndex(){
        return pageSize*(pageIndex-1);
    }

}
