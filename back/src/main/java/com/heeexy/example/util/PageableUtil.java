package com.heeexy.example.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtil {
    /**
     * 获取分页pagable
     *
     * @param page 页数
     * @return
     */
    public static Pageable getPageable(Integer page, Integer pageSize) {
        if (pageSize <= 0) {
            pageSize = 10;
        }
        Sort sort = new Sort(Sort.Direction.DESC, "createdDateTime");
        if (page == null) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        return pageable;
    }

    public static Pageable getPageable(Integer page, Integer pageSize,Sort.Direction direction,String properties) {
        if (pageSize <= 0) {
            pageSize = 10;
        }
        Sort sort = new Sort(direction, properties);
        if (page == null) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        return pageable;
    }
}
