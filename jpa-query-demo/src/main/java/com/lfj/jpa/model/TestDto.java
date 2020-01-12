package com.lfj.jpa.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
@ToString
public class TestDto {

    private String id;
    private String name;
    private String mobile;
    private Date createDate;


}
