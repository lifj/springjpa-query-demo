package com.lfj.jpa.repository.jpa;

import com.lfj.jpa.model.PageResult;
import com.lfj.jpa.utils.PageUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JpaNativeQueryImpl implements JpaNativeQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <T> List<T> queryListModel(Class<T> resultClass, String nativeSql, Object... params) {
        Query query = createNativeQuery(nativeSql, params);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(resultClass));
        return query.getResultList();
    }


    @Override
    public List<Map> queryListMap(String nativeSql, Object... params) {
        Query query = createNativeQuery(nativeSql, params);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    @Override
    public <T> PageResult<T> queryListModelPage(Class<T> resultClass, String nativeSql,
                                                PageRequest pageRequest, Object... params) {

        Query query = createNativeQuery(nativeSql, params);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(resultClass));
        query.setFirstResult(pageRequest.getOffset());
        query.setMaxResults(pageRequest.getPageSize());

        Long total = getTotal(nativeSql, params);

        Page<T> page = new PageImpl<>(query.getResultList(), pageRequest, total);
        return PageUtils.getPageResult(page);
    }

    @Override
    public PageResult<Map> queryListMapPage(String nativeSql, PageRequest pageRequest, Object... params) {
        Query query = createNativeQuery(nativeSql, params);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setFirstResult(pageRequest.getOffset());
        query.setMaxResults(pageRequest.getPageSize());

        Long total = getTotal(nativeSql, params);
        Page<Map> page = new PageImpl<Map>(query.getResultList(), pageRequest, total);
        return PageUtils.getPageResult(page);
    }

    private Long getTotal(String nativeSql, Object... params ){
        Query countQuery = createNativeQuery(" select count(1) from ( " + nativeSql + " )" + " a ", params);
        return Long.parseLong(countQuery.getSingleResult().toString());
    }


    private Query createNativeQuery(String sql,  Object... params){

        if(params != null && params.length ==1 && (params[0] instanceof Map)){
            Map paramMap = (Map) params[0];
            Map<String, Object> relaParam = new HashMap<>();
            paramMap.forEach((key, value) ->{
                relaParam.put(key.toString(), value);
            });
            return createMapParamNativeQuery(sql, relaParam);
        }

        return createObjNativeParamQuery(sql, params);
    }

    private Query createObjNativeParamQuery(String sql, Object... params){
        Query query = entityManager.createNativeQuery(sql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i + 1, params[i]);
            }
        }
        return query;
    }

    private Query createMapParamNativeQuery(String sql, Map<String, Object> relaParam) {
        Query query = entityManager.createNativeQuery(sql);
        if(CollectionUtils.isEmpty(relaParam)){
            return query;
        }
        relaParam.forEach(query::setParameter);
        return query;
    }


}
