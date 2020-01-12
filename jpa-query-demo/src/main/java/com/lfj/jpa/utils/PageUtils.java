package com.lfj.jpa.utils;

import com.lfj.jpa.model.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageUtils {

    public static<T> PageResult<T> getPageResult(Page<T> page){
        PageResult<T> pageResult = new PageResult<T>();
        pageResult.setContent(page.getContent());
        pageResult.setPageNum(page.getNumber());
        pageResult.setPageSize(page.getSize());
        pageResult.setTotalPage(page.getTotalPages());

        return pageResult;
    }

    public static PageRequest getPageRequest(int page, int pageSize){
        return new PageRequest(page, pageSize);
    }

    public static PageRequest getPageRequest(int page, int pageSize, Sort sort){
        return new PageRequest(page, pageSize, sort);
    }

}
