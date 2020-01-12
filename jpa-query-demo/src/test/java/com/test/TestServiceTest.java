package com.test;

import com.lfj.jpa.Application;
import com.lfj.jpa.entity.TestEntity;
import com.lfj.jpa.model.PageResult;
import com.lfj.jpa.model.TestDto;
import com.lfj.jpa.service.ITestService;
import com.lfj.jpa.utils.PageUtils;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestServiceTest {

    @Autowired
    private ITestService testService;

    @Test
    public void test(){
        TestEntity testEntity = testService.findOne("1");
        TestCase.assertEquals("1", testEntity.getId());

        List<TestEntity> testEntityList = testService.findByNameAndMobile("李一", "123456");

        TestEntity one = testService.findOneByNameAndMobile("李一", "123456");

        List<TestEntity> testLikeList = testService.findByNameLike("李%");

        List<TestEntity> testOrList = testService.findByNameOrMobile("李一", "898988");

        List<TestEntity> testBetweenList = testService.findByCreateDateBetween(new Date(), new Date());

        List<TestEntity> testLessTanList = testService.findByAgeLessThan(30);

        List<TestEntity> testCreateDateAfterList = testService.findByCreateDateAfter(new Date());

        List<TestEntity> testOrderList = testService.findByAgeOrderByNameDesc(29);

        List<String> nameList = new ArrayList<>();
        nameList.add("张三");
        nameList.add("李一");
        nameList.add("王五");
        nameList.add("马七");

        List<TestEntity> testInList = testService.findByNameIn(nameList);

        int page = 0;
        int pageSize = 3;
        Sort sort = new Sort(Sort.Direction.DESC, "age");
        PageRequest pageRequest = PageUtils.getPageRequest(page, pageSize, sort);
        PageResult<TestEntity> pageResult = testService.pageAllTest(pageRequest);
        log.info(pageResult.toString());

        //native query
        List<TestDto> testDtoList = testService.testNativeQuery("", "");
        List<TestDto> testDtoList2 = testService.testNativeQuery("李一", "");
        List<TestDto> testDtoList3 = testService.testNativeQuery("李一", "123456");


        PageResult<TestDto> page1 = testService.testNativeQueryPage(pageRequest);
        log.info(page1.toString());


        log.info("end");
    }

}
