package com.lfj.jpa.repository;

import com.lfj.jpa.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TestRepository extends JpaRepository<TestEntity, String> {

    List<TestEntity> findByNameLike(String name);

    List<TestEntity> findByNameAndMobile(String name, String mobile);

    TestEntity findFirstByNameAndMobile(String name, String mobile);

    List<TestEntity> findByNameOrMobile(String name, String mobile);

    List<TestEntity> findByCreateDateBetween(Date startDate, Date endDate);

    List<TestEntity> findByAgeLessThan(Integer age);

    List<TestEntity> findByCreateDateAfter(Date createDate);

    List<TestEntity> findByAgeOrderByNameDesc(int age);

    List<TestEntity> findByNameIn(List<String> nameList);


}
