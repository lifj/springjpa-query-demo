package com.lfj.jpa.service;

import com.lfj.jpa.entity.TestEntity;
import com.lfj.jpa.model.PageResult;
import com.lfj.jpa.model.TestDto;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

public interface ITestService {

    TestEntity findOne(String id);

    List<TestEntity> findByNameAndMobile(String name, String mobile);

    TestEntity findOneByNameAndMobile(String name, String mobile);

    List<TestEntity> findByNameLike(String name);

    List<TestEntity> findByNameOrMobile(String name, String mobile);

    List<TestEntity> findByCreateDateBetween(Date startDate, Date endDate);

    List<TestEntity> findByAgeLessThan(Integer age);

    List<TestEntity> findByCreateDateAfter(Date createDate);

    List<TestEntity> findByAgeOrderByNameDesc(int age);

    List<TestEntity> findByNameIn(List<String> nameList);

    PageResult<TestEntity> pageAllTest(PageRequest pageRequest);

    List<TestDto> testNativeQuery(String name, String mobile);

    PageResult<TestDto> testNativeQueryPage(PageRequest pageRequest);

}
