package com.anthonyzero.scaffold.common.utils;

import com.anthonyzero.scaffold.common.core.RequestQuery;
import com.anthonyzero.scaffold.common.core.SysConstant;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;

/**
 * 在分页之前设置Page参数 排序字段要求
 * 处理排序工具类
 * 
 * @author MrBird
 */
@SuppressWarnings("unchecked")
public class SortUtil {

    /**
     * 处理排序（分页情况下） for mybatis-plus
     *
     * @param request           QueryRequest
     * @param page              Page
     * @param defaultSort       默认排序的字段
     * @param defaultOrder      默认排序规则
     */
    public static void handlePageSort(RequestQuery request, Page page, String defaultSort, String defaultOrder) {
        page.setCurrent(request.getPageNum());
        page.setSize(request.getPageSize());
        String sortField = request.getField();
        if (StringUtils.isNotBlank(request.getField())
                && StringUtils.isNotBlank(request.getOrder())
                && !StringUtils.equalsIgnoreCase(request.getField(), "null")
                && !StringUtils.equalsIgnoreCase(request.getOrder(), "null")) {
            if (StringUtils.equals(request.getOrder(), SysConstant.ORDER_DESC))
                page.setDesc(sortField);
            else
                page.setAsc(sortField);
        } else {
            //request参数中没有排序要求 按照默认要求
            if (StringUtils.isNotBlank(defaultSort)) {
                if (StringUtils.equals(defaultOrder, SysConstant.ORDER_DESC))
                    page.setDesc(defaultSort);
                else
                    page.setAsc(defaultSort);
            }
        }
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param request QueryRequest
     * @param page    Page
     */
    public static void handlePageSort(RequestQuery request, Page page) {
        handlePageSort(request, page, null, null);
    }



    /**
     * 处理排序 for mybatis-plus
     *
     * @param request           QueryRequest
     * @param wrapper           wrapper
     * @param defaultSort       默认排序的字段
     * @param defaultOrder      默认排序规则
     */
    public static void handleWrapperSort(RequestQuery request, QueryWrapper wrapper, String defaultSort, String defaultOrder) {
        String sortField = request.getField();
        if (StringUtils.isNotBlank(request.getField())
                && StringUtils.isNotBlank(request.getOrder())
                && !StringUtils.equalsIgnoreCase(request.getField(), "null")
                && !StringUtils.equalsIgnoreCase(request.getOrder(), "null")) {
            if (StringUtils.equals(request.getOrder(), SysConstant.ORDER_DESC))
                wrapper.orderByDesc(sortField);
            else
                wrapper.orderByAsc(sortField);
        } else {
            if (StringUtils.isNotBlank(defaultSort)) {
                if (StringUtils.equals(defaultOrder, SysConstant.ORDER_DESC))
                    wrapper.orderByDesc(defaultSort);
                else
                    wrapper.orderByAsc(defaultSort);
            }
        }
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param request QueryRequest
     * @param wrapper wrapper
     */
    public static void handleWrapperSort(RequestQuery request, QueryWrapper wrapper) {
        handleWrapperSort(request, wrapper, null, null);
    }


}
