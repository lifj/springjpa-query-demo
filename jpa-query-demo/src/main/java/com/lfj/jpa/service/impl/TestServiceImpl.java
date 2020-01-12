package com.lfj.jpa.service.impl;

import com.lfj.jpa.entity.TestEntity;
import com.lfj.jpa.model.PageResult;
import com.lfj.jpa.model.TestDto;
import com.lfj.jpa.repository.TestRepository;
import com.lfj.jpa.repository.jpa.JpaNativeQuery;
import com.lfj.jpa.service.ITestService;
import com.lfj.jpa.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements ITestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private JpaNativeQuery jpaNativeQuery;


    @Override
    public TestEntity findOne(String id){
        return testRepository.findOne(id);
    }

    @Override
    public List<TestEntity> findByNameAndMobile(String name, String mobile){
        return testRepository.findByNameAndMobile(name, mobile);
    }

    @Override
    public TestEntity findOneByNameAndMobile(String name, String mobile){
        return testRepository.findFirstByNameAndMobile(name, mobile);
    }

    @Override
    public List<TestEntity> findByNameLike(String name){
        return testRepository.findByNameLike(name);
    }

    @Override
    public List<TestEntity> findByNameOrMobile(String name, String mobile){
        return testRepository.findByNameOrMobile(name, mobile);
    }

    @Override
    public List<TestEntity> findByCreateDateBetween(Date startDate, Date endDate){
        return testRepository.findByCreateDateBetween(startDate, endDate);
    }


    @Override
    public List<TestEntity> findByAgeLessThan(Integer age){
        return testRepository.findByAgeLessThan(age);
    }

    @Override
    public List<TestEntity> findByCreateDateAfter(Date createDate){
        return testRepository.findByCreateDateAfter(createDate);
    }

    @Override
    public List<TestEntity> findByAgeOrderByNameDesc(int age){
        return testRepository.findByAgeOrderByNameDesc(age);
    }

    @Override
    public List<TestEntity> findByNameIn(List<String> nameList){
        return testRepository.findByNameIn(nameList);
    }

    @Override
    public PageResult<TestEntity> pageAllTest(PageRequest pageRequest) {
        Page<TestEntity> page =  testRepository.findAll(pageRequest);
        return PageUtils.getPageResult(page);
    }

    @Override
    public List<TestDto> testNativeQuery(String name, String mobile) {
        Map<String, Object> paramMap = new HashMap<>();

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select id, name, mobile, create_date as createDate from demo_test where 1=1 ");
        if(!StringUtils.isEmpty(name)){
            sqlBuilder.append(" AND name = :name");
            paramMap.put("name", name);
        }
        if(!StringUtils.isEmpty(mobile)){
            sqlBuilder.append(" AND mobile = :mobile");
            paramMap.put("mobile", mobile);
        }
        return jpaNativeQuery.queryListModel(TestDto.class, sqlBuilder.toString(), paramMap);

    }

    @Override
    public PageResult<TestDto> testNativeQueryPage(PageRequest pageRequest) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select id, name, mobile, create_date as createDate from demo_test ");
        return jpaNativeQuery.queryListModelPage(TestDto.class, sqlBuilder.toString(), pageRequest, null);
    }


}
