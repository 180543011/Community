package com.zhiling.z.community.utils;

import com.zhiling.z.community.dto.PageDTO;

/**
 * @Author zlhl
 * @Date 2019/12/24
 * @Version V1.0
 **/
public class PageUtil {

    /**
     *  对分页对象进行处理
     * @param pageDTO 分页对象
     */
    public static void dealWithPage(PageDTO pageDTO){
        //判断页面行数是否超标
        if (pageDTO.getPageSize()<=0){
            pageDTO.setPageSize(10);
        }
        pageDTO.setPageCount((pageDTO.getCounts() -1)/ pageDTO.getPageSize()+1);
        //判断index是否超标
        if (pageDTO.getPageIndex()<=0){
            pageDTO.setPageIndex(1);
        }
        if(pageDTO.getPageIndex()>pageDTO.getPageCount()){
            pageDTO.setPageIndex(pageDTO.getPageCount());
        }
        //获取分页条个数
        //获取并判断分页条数字是否符合情况
        int totalPage = pageDTO.getPageIndex() - 3;
        if (totalPage<=0){
            totalPage = 1;
        }
        int endPage = pageDTO.getPageIndex() + 3;
        if (endPage>pageDTO.getPageCount()){
            endPage = pageDTO.getPageCount();
        }
        for (int i = totalPage; i<= endPage; i++){
            pageDTO.getPages().add(i);
        }
        //当前页为第一页不显示上一页按钮
        if (pageDTO.getPageIndex()==1){
            pageDTO.setShowPrevious(false);
        }else {
            pageDTO.setShowPrevious(true);
        }
        //当前页为最后一页不显示下一页按钮
        if (pageDTO.getPageIndex().equals(pageDTO.getPageCount())){
            pageDTO.setShowNext(false);
        }else {
            pageDTO.setShowNext(true);
        }
        //是否展示第一页
        if (pageDTO.getPages().contains(1)){
            pageDTO.setShowFirstPage(false);
        }else {
            pageDTO.setShowFirstPage(true);
        }
        //是否展示最后一页
        if (pageDTO.getPages().contains(pageDTO.getPageCount())){
            pageDTO.setShowEndPage(false);
        }else {
            pageDTO.setShowEndPage(true);
        }
    }

}
