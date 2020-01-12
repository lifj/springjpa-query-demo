package com.lfj.jpa.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class PageResult<T> {

    private Integer pageNum;
    private Integer pageSize;
    private List<T> content;
    private Integer totalPage;

}
