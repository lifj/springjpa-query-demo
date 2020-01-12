package com.lfj.jpa.repository.jpa;


import com.lfj.jpa.model.PageResult;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

public interface JpaNativeQuery {

    /**
     * 查询返回List<Model>
     * @param resultClass
     * @param nativeSql
     * @param params
     * @param <T>
     * @return
     */
    <T> List<T> queryListModel(Class<T> resultClass, String nativeSql, Object... params);

    /**
     * 查询返回 List<Map>
     * @param params
     * @param nativeSql
     * @return
     */
     List<Map> queryListMap(String nativeSql, Object... params);


    /**
     *  分页查询,返回实体
     * @param resultClass
     * @param nativeSql
     * @param pageRequest
     * @param params
     * @param <T>
     * @return
     */
     <T> PageResult<T> queryListModelPage(Class<T> resultClass, String nativeSql, PageRequest pageRequest, Object... params);

    /**
     * 分页查询，返回Map
     * @param nativeSql
     * @param pageRequest
     * @param params
     * @return
     */
    PageResult<Map> queryListMapPage(String nativeSql, PageRequest pageRequest, Object... params);

}
